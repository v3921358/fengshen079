package handling.channel.handler;

import client.inventory.Equip;
import client.inventory.Item;
import client.inventory.MapleInventoryType;
import client.MapleClient;
import client.MapleCharacter;
import constants.GameConstants;
import client.MapleQuestStatus;
import client.RockPaperScissors;
import client.inventory.ItemFlag;
import constants.QuickMove;
import constants.ServerConfig;
import constants.ServerConstants;
import handling.SendPacketOpcode;
import handling.world.World;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import server.shops.MapleShop;
import server.MapleInventoryManipulator;
import server.MapleStorage;
import server.life.MapleNPC;
import server.quest.MapleQuest;
import scripting.NPCScriptManager;
import scripting.NPCConversationManager;
import server.AutobanManager;
import server.MapleItemInformationProvider;
import server.maps.MapScriptMethods;
import server.maps.MapleMap;
import tools.FileoutputUtil;
import tools.packet.CField;
import tools.Pair;
import tools.data.LittleEndianAccessor;
import tools.data.MaplePacketLittleEndianWriter;
import tools.packet.CField.EffectPacket;
import tools.packet.CWvsContext;

public class NPCHandler {
    private static final int bankHandlingFee = ServerConfig.bankHandlingFee;
    private static final int takeOutHandlingFee = ServerConfig.takeOutHandlingFee;
    public static void NPCAnimation(LittleEndianAccessor slea, MapleClient c) {
    
        final int length = (int) slea.available();
        if (length < 4) {
            return;
        }

        MapleMap map = c.getPlayer().getMap();
        if (map == null) {
            return;
        }

        int oid = slea.readInt();
        MapleNPC npc = map.getNPCByOid(oid);
        if (npc == null) {
            if (c.getPlayer().isAdmin()) {
                c.getPlayer().dropMessage("NPC OID =" + oid);
            }
            return;
        }

        switch (npc.getId()) {
          //  case 1010100:
            //case 1012003:
            //case 1012106:
            //case 1052103:
            //case 1061100:
            //case 1032004:
            //case 2103:
            //case 10000:
             case 0:
                return;
        }

        if (!c.getPlayer().isMapObjectVisible(npc)) {
            return;
        }

        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(SendPacketOpcode.NPC_ACTION.getValue());

        mplew.writeInt(oid);
        if (length == 6) { // NPC Talk
            mplew.writeShort(slea.readShort());
        } else if (length > 9) { // NPC Move
            mplew.write(slea.read(length - 13));
        } else {
            if (c.getPlayer().isAdmin()) {
                c.getPlayer().dropMessage("NPC, Packet:" + slea.toString());
            }
            return;
        }
        c.getSession().write(mplew.getPacket());
    }

    public static final void NPCShop(LittleEndianAccessor slea, MapleClient c, MapleCharacter chr) {
        byte bmode = slea.readByte();
        if (chr == null) {
            return;
        }

        switch (bmode) {
            case 0: {
                MapleShop shop = chr.getShop();
                if (shop == null) {
                    return;
                }
                short slot = slea.readShort();
                slot++;
                int itemId = slea.readInt();
                short quantity = slea.readShort();
                // int unitprice = slea.readInt();
                shop.buy(c, slot, itemId, quantity);
                break;
            }
            case 1: {
                MapleShop shop = chr.getShop();
                if (shop == null) {
                    return;
                }
                byte slot = (byte) slea.readShort();
                int itemId = slea.readInt();
                short quantity = slea.readShort();
                shop.sell(c, GameConstants.getInventoryType(itemId), slot, quantity);
                break;
            }
            case 2: {
                MapleShop shop = chr.getShop();
                if (shop == null) {
                    return;
                }
                byte slot = (byte) slea.readShort();
                shop.recharge(c, slot);
                break;
            }
            default:
                chr.setConversation(0);
        }
    }

    public static final void NPCTalk(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null || chr.getMap() == null) {
            return;
        }
        final MapleNPC npc = chr.getMap().getNPCByOid(slea.readInt());
        if (npc == null) {
            return;
        }
        if (chr.hasBlockedInventory()) {
            chr.dropMessage(-1, "?????????????????????????????????npc??????,?????????????????? @??????/@ea ????????????????????????");
            return;
        }
        /*if (NPCScriptManager.getInstance().hasScript(c, npc.getId(), null)) { //I want it to come before shop
            NPCScriptManager.getInstance().start(c, npc.getId(), null);
        } else */
        if (npc.hasShop()) {
            chr.setConversation(1);
            npc.sendShop(c);
        } else {
            NPCScriptManager.getInstance().start(c, npc.getId(), null);
        }
    }

    public static final void QuestAction(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        final byte action = slea.readByte();
        int quest = slea.readUShort();
        if (quest == 20734) {
            c.getSession().write(CWvsContext.ultimateExplorer());
            return;
        }
        if (chr == null) {
            return;
        }
        final MapleQuest q = MapleQuest.getInstance(quest);
        switch (action) {
            case 0: { // Restore lost item
                //chr.updateTick(slea.readInt());
                slea.readInt();
                final int itemid = slea.readInt();
                q.RestoreLostItem(chr, itemid);
                break;
            }
            case 1: { // Start Quest
                final int npc = slea.readInt();
                if (npc == 0 && quest > 0) {
                    q.forceStart(chr, npc, null);
                } else if (!q.hasStartScript()) {
                    q.start(chr, npc);
                }
                if (chr.isGM() || ServerConfig.logPackets) {
                    chr.dropMessage(6, "?????????????????? NPC: " + npc + " Quest???" + quest);
                }
                break;
            }
            case 2: { // Complete Quest
                final int npc = slea.readInt();
                //chr.updateTick(slea.readInt());
                slea.readInt();
                if (q.hasEndScript()) {
                    return;
                }
                if (slea.available() >= 4) {
                    q.complete(chr, npc, slea.readInt());
                } else {
                    q.complete(chr, npc);
                }
                if (chr.isGM() || ServerConfig.logPackets) {
                    chr.dropMessage(6, "?????????????????? NPC: " + npc + " Quest: " + quest);
                }
                // c.getSession().write(CField.completeQuest(c.getPlayer(), quest));
                //c.getSession().write(CField.updateQuestInfo(c.getPlayer(), quest, npc, (byte)14));
                // 6 = start quest
                // 7 = unknown error
                // 8 = equip is full
                // 9 = not enough mesos
                // 11 = due to the equipment currently being worn wtf o.o
                // 12 = you may not posess more than one of this item
                break;
            }
            case 3: { // Forfeit Quest
                if (GameConstants.canForfeit(q.getId())) {
                    q.forfeit(chr);
                } else {
                    chr.dropMessage(1, "???????????????????????????");
                }
                if (chr.isGM() || ServerConfig.logPackets) {
                    chr.dropMessage(6, "?????????????????? Quest: " + quest);
                }
                break;
            }
            case 4: { // Scripted Start Quest
                final int npc = slea.readInt();
                if (chr.hasBlockedInventory()) {
                    chr.dropMessage(-1, "?????????????????????????????????npc??????,?????????????????? @??????/@ea ????????????????????????");
                    return;
                }
                if (!q.canStart(chr, npc)) {
                    return;
                }
                //c.getPlayer().updateTick(slea.readInt());
                NPCScriptManager.getInstance().startQuest(c, npc, quest);
                if (chr.isGM() || ServerConfig.logPackets) {
                    chr.dropMessage(6, "?????????????????? NPC???" + npc + " Quest: " + quest);
                }
                break;
            }
            case 5: { // Scripted End Quest
                final int npc = slea.readInt();
                if (chr.hasBlockedInventory()) {
                    chr.dropMessage(-1, "?????????????????????????????????npc??????,?????????????????? @??????/@ea ????????????????????????");
                    return;
                }
                if (!q.canComplete(chr, npc)) {
                    return;
                }
                //c.getPlayer().updateTick(slea.readInt());
                NPCScriptManager.getInstance().endQuest(c, npc, quest, false);
                c.getSession().write(EffectPacket.showForeignEffect(10)); // Quest completion
                chr.getMap().broadcastMessage(chr, EffectPacket.showForeignEffect(chr.getId(), 10), false);
                if (chr.isGM() || ServerConfig.logPackets) {
                    chr.dropMessage(6, "?????????????????? NPC???" + npc + " Quest: " + quest);
                }
                break;
            }
        }
    }

    public static final void QuestWarp(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (!chr.isAlive() || chr.getCSPoints(2) < 200) {
            chr.dropMessage(1, "??????????????????????????????");
            c.getSession().write(CWvsContext.enableActions());
            return;
        }
        final byte action = (byte) (slea.readByte() + 1);
        int quest = slea.readUShort();
        final MapleQuest q = MapleQuest.getInstance(quest);
        switch (action) {
            case 2: { // Complete Quest
                final int npc = slea.readInt();
                q.complete(chr, npc);
                break;
            }
        }
        NPCScriptManager.getInstance().dispose(c);
        c.getSession().write(CWvsContext.enableActions());
        chr.modifyCSPoints(2, -200);
    }

    @SuppressWarnings("empty-statement")
    public static final void Storage(LittleEndianAccessor slea, MapleClient c, MapleCharacter chr) {
        byte mode = slea.readByte();
        if (chr == null) {
            return;
        }
        MapleStorage storage = chr.getStorage();

        switch (mode) {
            case 4: {// ???????????? - ????????????
                int needmeso = chr.getMapId() == 910000000 ? 1000 : 0;
                byte type = slea.readByte();
                byte slot = storage.getSlot(MapleInventoryType.getByType(type), slea.readByte());
                Item item = storage.takeOut(slot);

                if (item != null) {
                    if (needmeso > 0) {
                        if (chr.getMeso() < needmeso) {
                            storage.store(item);
                            chr.dropMessage(1, "????????????????????????????????????");
                            return;
                        }
                        chr.gainMeso(-needmeso, true);
                    }
                    if (!MapleInventoryManipulator.checkSpace(c, item.getItemId(), item.getQuantity(), item.getOwner())) {
                        // ???????????????????????????
                        storage.store(item);
                        chr.dropMessage(1, "??????????????????");
                    } else {
                        MapleInventoryManipulator.addFromDrop(c, item, false);
                        storage.sendTakenOut(c, GameConstants.getInventoryType(item.getItemId()));
                    }
                    if (ServerConfig.logs_storage) {
                        FileoutputUtil.logToFile("logs/????????????/????????????.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSessionIPAddress() + " ??????: " + c.getAccountName() + " ??????: " + c.getPlayer().getName() + " ?????????????????????????????????:" + item.getItemId() + " ?????????" + item.getQuantity());
                    }
                } else {
                    c.getSession().write(CWvsContext.enableActions());
                }
                break;
            }
            case 5: {//???????????? - ????????????
                byte slot = (byte) slea.readShort();
                int itemId = slea.readInt();
                MapleInventoryType type = GameConstants.getInventoryType(itemId);
                short quantity = slea.readShort();
                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                if (quantity < 1) {
                    return;
                }
                if (storage.isFull()) {
                    c.getSession().write(CField.NPCPacket.getStorageFull());
                    return;
                }
                if (chr.getInventory(type).getItem((short) slot) == null) {
                    c.getSession().write(CWvsContext.enableActions());
                    return;
                }

                if (chr.getMeso() < 100L) {
                    chr.dropMessage(1, "????????????????????????????????????");
                } else {
                    Item item = chr.getInventory(type).getItem((short) slot).copy();

                    if (GameConstants.isPet(item.getItemId())) {
                        c.getSession().write(CWvsContext.enableActions());
                        return;
                    }
                    short flag = item.getFlag();
                    if ((ii.isPickupRestricted(item.getItemId())) && (storage.findById(item.getItemId()) != null)) {
                        c.getSession().write(CWvsContext.enableActions());
                        return;
                    }
                    if ((item.getItemId() == itemId) && ((item.getQuantity() >= quantity) || (GameConstants.isThrowingStar(itemId)) || (GameConstants.isBullet(itemId)))) {
                        if (ii.isDropRestricted(item.getItemId())) {
                            if (ItemFlag.KARMA_EQ.check(flag)) {
                                item.setFlag((short) (flag - ItemFlag.KARMA_EQ.getValue()));
                            } else if (ItemFlag.KARMA_USE.check(flag)) {
                                item.setFlag((short) (flag - ItemFlag.KARMA_USE.getValue()));
                            } else if (ItemFlag.KARMA_ACC.check(flag)) {
                                item.setFlag((short) (flag - ItemFlag.KARMA_ACC.getValue()));
                            } else if (ItemFlag.KARMA_ACC_USE.check(flag)) {
                                item.setFlag((short) (flag - ItemFlag.KARMA_ACC_USE.getValue()));
                            } else {
                                c.getSession().write(CWvsContext.enableActions());
                                return;
                            }
                        }
                        if ((GameConstants.isThrowingStar(itemId)) || (GameConstants.isBullet(itemId))) {
                            quantity = item.getQuantity();
                        }
                        chr.gainMeso(-100L, false, false);
                        MapleInventoryManipulator.removeFromSlot(c, type, (short) slot, quantity, false);
                        item.setQuantity(quantity);
                        storage.store(item);
                        if (ServerConfig.logs_storage) {
                            FileoutputUtil.logToFile("logs/????????????/????????????.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSessionIPAddress() + " ??????: " + c.getAccountName() + " ??????: " + c.getPlayer().getName() + " ?????????????????????????????????:" + item.getItemId() + " ?????????" + item.getQuantity());
                        }
                    } else {
                        return;
                    }
                }
                storage.sendStored(c, GameConstants.getInventoryType(itemId));
                break;
            }
            case 6:
                storage.arrange();
                storage.update(c);
                break;
                
       /*     case 7: {// ???????????????????????????
                int meso = slea.readInt();
                int storageMesos = (int) storage.getMeso();
                int playerMesos = (int) chr.getMeso();

                if (((meso > 0) && (storageMesos >= meso)) || ((meso < 0) && (playerMesos >= -meso))) {
                    if ((meso < 0) && (storageMesos - meso < 0)) {
                        meso = -(Integer.MAX_VALUE - storageMesos);
                        if (-meso <= playerMesos) {
                            return;
                        }
                    } else if ((meso > 0L) && (playerMesos + meso < 0)) {
                        meso = (Integer.MAX_VALUE - playerMesos);
                        if (meso > storageMesos) {
                            return;
                        }
                    }
                    if (Math.abs(meso) < bankHandlingFee) {
                        chr.dropMessage(5, "??????" + (meso < 0 ? "??????" : "??????") + "?????????????????????????????????(" + bankHandlingFee + ")????????????????????????");
                        storage.sendMeso(c);
                        break;
                    }
                    
                    chr.dropMessage(String.format("%s?????????%s ????????????%s ????????????%s", (meso < 0 ? "??????" : "??????"), (Math.abs(meso) - bankHandlingFee), bankHandlingFee, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
                    
                    if (meso < 0) { // ??????
                        storage.setMeso(storageMesos - meso - bankHandlingFee);
                         } else { // ??????
                        storage.setMeso(storageMesos - meso);
                        chr.gainMeso(meso - bankHandlingFee, false, true, false);
                    }
                 //   chr.gainMeso(meso, false, false);
                    if (ServerConfig.logs_storage) {
                        FileoutputUtil.logToFile("logs/????????????/????????????.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSessionIPAddress() + " ??????: " + c.getAccountName() + " ??????: " + c.getPlayer().getName() + " ?????????????????????????????????:" + meso);
                    }
                } else {
                    return;
                }
                storage.sendMeso(c);
                break;
            }*/
            case 7: {
                int meso = slea.readInt();
                int storageMesos = (int) storage.getMeso();
                int playerMesos = (int) chr.getMeso();

                if (((meso > 0L) && (storageMesos >= meso)) || ((meso < 0L) && (playerMesos >= -meso))) {
                    if ((meso < 0L) && (storageMesos - meso < 0L)) {
                        meso = -(2147483647 - storageMesos);
                        if (-meso <= playerMesos);
                    } else if ((meso > 0L) && (playerMesos + meso < 0L)) {
                        meso = 2147483647 - playerMesos;
                        if (meso > storageMesos) {
                            return;
                        }
                    }
                    storage.setMeso(storageMesos - meso);
                    chr.gainMeso(meso, false, false);
                } else {
                    return;
                }
                storage.sendMeso(c);
                break;
            }
            case 8:
                storage.close();
                chr.setConversation(0);
                break;
            default:
                System.out.println("Unhandled Storage mode : " + mode);
        }
    }

    public static void NPCMoreTalk(final LittleEndianAccessor slea, final MapleClient c) {
        final byte lastMsg = slea.readByte(); // 00 (last msg type I think)

        final byte action = slea.readByte(); // 00 = end chat, 01 == follow

        final NPCConversationManager cm = NPCScriptManager.getInstance().getCM(c);

        if (cm == null || c.getPlayer().getConversation() == 0 || cm.getLastMsg() != lastMsg) {
            return;
        }
        cm.setLastMsg((byte) -1);
        /*if (lastMsg == 1) {
            NPCScriptManager.getInstance().action(c, action, lastMsg, -1);*???
        } else*/ if (lastMsg == 2) {
            if (action != 0) {
                cm.setGetText(slea.readMapleAsciiString());
                if (cm.getType() == 0) {
                    NPCScriptManager.getInstance().startQuest(c, action, lastMsg, -1);
                } else if (cm.getType() == 1) {
                    NPCScriptManager.getInstance().endQuest(c, action, lastMsg, -1);
                } else {
                    NPCScriptManager.getInstance().action(c, action, lastMsg, -1);
                }
            } else {
                cm.dispose();
            }
        } else if (lastMsg == 0x17) {
            NPCScriptManager.getInstance().action(c, (byte) 1, lastMsg, action);
        } else {
            int selection = -1;
            if (slea.available() >= 4) {
                selection = slea.readInt();
            } else if (slea.available() > 0) {
                selection = slea.readByte();
            }
            if (lastMsg == 4 && selection == -1) {
                cm.dispose();
                return;//h4x
            }
            if (selection >= -1 && action != -1) {
                if (cm.getType() == 0) {
                    NPCScriptManager.getInstance().startQuest(c, action, lastMsg, selection);
                } else if (cm.getType() == 1) {
                    NPCScriptManager.getInstance().endQuest(c, action, lastMsg, selection);
                } else {
                    NPCScriptManager.getInstance().action(c, action, lastMsg, selection);
                }
            } else {
                cm.dispose();
            }
        }
    }

    public static final void repairAll(final MapleClient c) {
        Equip eq;
        double rPercentage;
        int price = 0;
        Map<String, Integer> eqStats;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final Map<Equip, Integer> eqs = new HashMap<>();
        final MapleInventoryType[] types = {MapleInventoryType.EQUIP, MapleInventoryType.EQUIPPED};
        for (MapleInventoryType type : types) {
            for (Item item : c.getPlayer().getInventory(type).newList()) {
                if (item instanceof Equip) { //redundant
                    eq = (Equip) item;
                    if (eq.getDurability() >= 0) {
                        eqStats = ii.getEquipStats(eq.getItemId());
                        if (eqStats.containsKey("durability") && eqStats.get("durability") > 0 && eq.getDurability() < eqStats.get("durability")) {
                            rPercentage = (100.0 - Math.ceil((eq.getDurability() * 1000.0) / (eqStats.get("durability") * 10.0)));
                            eqs.put(eq, eqStats.get("durability"));
                            price += (int) Math.ceil(rPercentage * ii.getPrice(eq.getItemId()) / (ii.getReqLevel(eq.getItemId()) < 70 ? 100.0 : 1.0));
                        }
                    }
                }
            }
        }
        if (eqs.size() <= 0 || c.getPlayer().getMeso() < price) {
            return;
        }
        c.getPlayer().gainMeso(-price, true);
        Equip ez;
        for (Entry<Equip, Integer> eqqz : eqs.entrySet()) {
            ez = eqqz.getKey();
            ez.setDurability(eqqz.getValue());
            c.getPlayer().forceReAddItem(ez.copy(), ez.getPosition() < 0 ? MapleInventoryType.EQUIPPED : MapleInventoryType.EQUIP);
        }
    }

    public static final void repair(final LittleEndianAccessor slea, final MapleClient c) {
        if (slea.available() < 4) { //leafre for now
            return;
        }
        final int position = slea.readInt(); //who knows why this is a int
        final MapleInventoryType type = position < 0 ? MapleInventoryType.EQUIPPED : MapleInventoryType.EQUIP;
        final Item item = c.getPlayer().getInventory(type).getItem((byte) position);
        if (item == null) {
            return;
        }
        final Equip eq = (Equip) item;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final Map<String, Integer> eqStats = ii.getEquipStats(item.getItemId());
        if (eq.getDurability() < 0 || !eqStats.containsKey("durability") || eqStats.get("durability") <= 0 || eq.getDurability() >= eqStats.get("durability")) {
            return;
        }
        final double rPercentage = (100.0 - Math.ceil((eq.getDurability() * 1000.0) / (eqStats.get("durability") * 10.0)));
        //drpq level 105 weapons - ~420k per %; 2k per durability point
        //explorer level 30 weapons - ~10 mesos per %
        final int price = (int) Math.ceil(rPercentage * ii.getPrice(eq.getItemId()) / (ii.getReqLevel(eq.getItemId()) < 70 ? 100.0 : 1.0)); // / 100 for level 30?
        //TODO: need more data on calculating off client
        if (c.getPlayer().getMeso() < price) {
            return;
        }
        c.getPlayer().gainMeso(-price, false);
        eq.setDurability(eqStats.get("durability"));
        c.getPlayer().forceReAddItem(eq.copy(), type);
    }

    public static final void UpdateQuest(final LittleEndianAccessor slea, final MapleClient c) {
        final MapleQuest quest = MapleQuest.getInstance(slea.readShort());
        if (quest != null) {
            c.getPlayer().updateQuest(c.getPlayer().getQuest(quest), true);
        }
    }

    public static final void UseItemQuest(final LittleEndianAccessor slea, final MapleClient c) {
        final short slot = slea.readShort();
        final int itemId = slea.readInt();
        final Item item = c.getPlayer().getInventory(MapleInventoryType.ETC).getItem(slot);
        final int qid = slea.readInt();
        final MapleQuest quest = MapleQuest.getInstance(qid);
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        Pair<Integer, List<Integer>> questItemInfo = null;
        boolean found = false;
        for (Item i : c.getPlayer().getInventory(MapleInventoryType.ETC)) {
            if (i.getItemId() / 10000 == 422) {
                questItemInfo = ii.questItemInfo(i.getItemId());
                if (questItemInfo != null && questItemInfo.getLeft() == qid && questItemInfo.getRight() != null && questItemInfo.getRight().contains(itemId)) {
                    found = true;
                    break; //i believe it's any order
                }
            }
        }
        if (quest != null && found && item != null && item.getQuantity() > 0 && item.getItemId() == itemId) {
            final int newData = slea.readInt();
            final MapleQuestStatus stats = c.getPlayer().getQuestNoAdd(quest);
            if (stats != null && stats.getStatus() == 1) {
                stats.setCustomData(String.valueOf(newData));
                c.getPlayer().updateQuest(stats, true);
                MapleInventoryManipulator.removeFromSlot(c, MapleInventoryType.ETC, slot, (short) 1, false);
            }
        }
    }

    public static final void RPSGame(LittleEndianAccessor slea, MapleClient c) {
        if ((slea.available() == 0L) || (c.getPlayer() == null) || (c.getPlayer().getMap() == null) || (!c.getPlayer().getMap().containsNPC(9000019))) {
            if ((c.getPlayer() != null) && (c.getPlayer().getRPS() != null)) {
                c.getPlayer().getRPS().dispose(c);
            }
            return;
        }
        byte mode = slea.readByte();
        switch (mode) {
            case 0:
            case 5:
                if (c.getPlayer().getRPS() != null) {
                    c.getPlayer().getRPS().reward(c);
                }
                if (c.getPlayer().getMeso() >= 1000L) {
                    c.getPlayer().setRPS(new RockPaperScissors(c, mode));
                } else {
                    c.getSession().write(CField.getRPSMode((byte) 8, -1, -1, -1));
                }
                break;
            case 1:
                if ((c.getPlayer().getRPS() == null) || (!c.getPlayer().getRPS().answer(c, slea.readByte()))) {
                    c.getSession().write(CField.getRPSMode((byte) 13, -1, -1, -1));
                }
                break;
            case 2:
                if ((c.getPlayer().getRPS() == null) || (!c.getPlayer().getRPS().timeOut(c))) {
                    c.getSession().write(CField.getRPSMode((byte) 13, -1, -1, -1));
                }
                break;
            case 3:
                if ((c.getPlayer().getRPS() == null) || (!c.getPlayer().getRPS().nextRound(c))) {
                    c.getSession().write(CField.getRPSMode((byte) 13, -1, -1, -1));
                }
                break;
            case 4:
                if (c.getPlayer().getRPS() != null) {
                    c.getPlayer().getRPS().dispose(c);
                } else {
                    c.getSession().write(CField.getRPSMode((byte) 13, -1, -1, -1));
                }
                break;
        }
    }

    public static void OpenQuickMove(final LittleEndianAccessor slea, final MapleClient c) {
        final int npcid = slea.readInt();
        if (c.getPlayer().hasBlockedInventory() || c.getPlayer().isInBlockedMap() || c.getPlayer().getLevel() < 10) {
            return;
        }
        for (QuickMove qm : QuickMove.values()) {
            if (qm.getMap() == c.getPlayer().getMapId()) {
                List<QuickMove.QuickMoveNPC> qmn = new LinkedList();
                int npcs = qm.getNPCFlag();
                for (QuickMove.QuickMoveNPC npc : QuickMove.QuickMoveNPC.values()) {
                    if ((npcs & npc.getValue()) != 0 && npc.getId() == npcid) {
                        NPCScriptManager.getInstance().start(c, npcid, null);
                        break;
                    }
                }
            }
        }
    }

    public static Invocable getInvocable(String path, MapleClient c, boolean npc) {
        ScriptEngineManager sem = new ScriptEngineManager();
        FileReader fr = null;
        try {
            path = "scripts/" + path;
            ScriptEngine engine = null;

            if (c != null) {
                engine = c.getScriptEngine(path);
            }
            if (engine == null) {
                File scriptFile = new File(path);
                if (!scriptFile.exists()) {
                    return null;
                }
                engine = sem.getEngineByName("javascript");
                if (c != null) {
                    c.setScriptEngine(path, engine);
                }
                fr = new FileReader(scriptFile);
                engine.eval(fr);
            } else if (c != null && npc) {
                c.removeClickedNPC();
                NPCScriptManager.getInstance().dispose(c);
                c.getSession().write(CWvsContext.enableActions());
                c.getPlayer().dropMessage(-1, "?????????????????????????????????npc??????,?????????????????? @??????/@ea ????????????????????????");
            }
            return (Invocable) engine;
        } catch (FileNotFoundException | ScriptException e) {
            System.err.println("Error executing script. Path: " + path + "\nException " + e);
            FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Error executing script. Path: " + path + "\nException " + e);
            return null;
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    private NPCHandler() {
    }
}
