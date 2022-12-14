/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import handling.world.World;
import java.util.concurrent.ScheduledFuture;
import scripting.LieDetectorScript;
import server.Timer;
import server.maps.MapleMap;
import server.quest.MapleQuest;
import tools.HexTool;
import tools.Pair;
import tools.packet.CWvsContext;

/**
 *
 * @author alienware
 */
public class MapleLieDetector {

    //public MapleCharacter chr;
    public byte type;
    public int attempt;
    public int cid;
    public String tester;
    public String answer;
    public boolean inProgress;
    public boolean passed;
    public long lasttime;
    public ScheduledFuture<?> schedule;

    /*public MapleLieDetector(MapleCharacter c) {
        this.chr = c;
        reset();
    }*/
    public MapleLieDetector(int chid) {
        cid = chid;
        reset();
    }

    public final boolean startLieDetector(final String tester, final boolean isItem, final boolean anotherAttempt) {
        if ((!anotherAttempt) && (((isPassed()) && (isItem)) || (inProgress()) || (this.attempt == 3))) {
            return false;
        }
        Pair captcha = LieDetectorScript.getImageBytes();
        if (captcha == null) {
            return false;
        }
        byte[] image = HexTool.getByteArrayFromHexString((String) captcha.getLeft());
        this.answer = ((String) captcha.getRight());
        this.tester = tester;
        this.inProgress = true;
        this.type = (byte) (isItem ? 0 : 1);
        this.attempt += 1;
        MapleCharacter chrid = MapleCharacter.getOnlineCharacterById(cid);
        if (this.attempt < 3) {
            if (chrid != null) {
                chrid.getClient().getSession().write(CWvsContext.sendLieDetector(image, this.attempt));
            }
        }
        schedule = Timer.EtcTimer.getInstance().schedule(new Runnable() {
            public void run() {
                MapleCharacter searchchr = MapleCharacter.getOnlineCharacterById(cid);
                if (((!MapleLieDetector.this.isPassed()) /*&& (!isItem)*/) && (searchchr != null)) {
                    if (MapleLieDetector.this.attempt >= 3) {
                        MapleCharacter search_chr = searchchr.getMap().getCharacterByName(tester);
                        if ((search_chr != null) && (search_chr.getId() != searchchr.getId())) {
                            search_chr.dropMessage(5, searchchr.getName() + " ???????????????????????????");
                            //FileoutputUtil.logToFile("logs/Data/????????????.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + searchchr.getClient().getSession().remoteAddress().toString().split(":")[0] + " ??????: " + searchchr.getClient().getAccountName() + " ??????: " + searchchr.getClient().getPlayer().getName() + " ????????????????????????");
                        }
                        MapleLieDetector.this.end();
                        searchchr.getClient().getSession().write(CWvsContext.LieDetectorResponse((byte) 7, (byte) 0));
                        MapleMap map = searchchr.getMap().getReturnMap();
                        searchchr.changeMap(map, map.getPortal(0));
                        World.Broadcast.broadcastGMMessage(CWvsContext.broadcastMsg(6, "[????????????] ??????: " + searchchr.getName() + " (?????? " + searchchr.getLevel() + ") ???????????????????????????"));
                    } else {
                        MapleLieDetector.this.startLieDetector(tester, isItem, true);
                    }
                }
            }
        }, 60000);

        return true;
    }

    public final int getAttempt() {
        return this.attempt;
    }

    public final byte getLastType() {
        return this.type;
    }

    public final String getTester() {
        return this.tester;
    }

    public final String getAnswer() {
        return this.answer;
    }

    public final boolean inProgress() {
        return this.inProgress;
    }

    public final boolean isPassed() {
        return this.passed;
    }

    public void setPassed(boolean passedi) {
        passed = passedi;
    }

    public final boolean canDetector(long time) {
        return lasttime + 300000 > time;
    }

    public final void end() {
        this.inProgress = false;
        this.passed = true;
        this.attempt = 0;
        lasttime = System.currentTimeMillis();
        if (schedule != null) {
            schedule.cancel(false);
            schedule = null;
        }
    }

    public final void reset() {
        this.tester = "";
        this.answer = "";
        this.attempt = 0;
        this.inProgress = false;
        this.passed = false;
    }
}
