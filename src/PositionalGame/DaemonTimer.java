package PositionalGame;

public class DaemonTimer implements Runnable {
    Game currentGame;
    int durataMeci;

    DaemonTimer(Game currentGame, int durataMeci){
        this.currentGame = currentGame;
        this.durataMeci = durataMeci;
    }

    public void run() {
        long start = System.currentTimeMillis();
        while(start_timer(start) && gameContinue()){
        }
        try {
            long stop = System.currentTimeMillis();
            System.out.println("Jocul a durat:" + (stop - start)/1000 + " secunde");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean start_timer(long start) {
        long stop = System.currentTimeMillis();
        if(stop - start > durataMeci*1000) {
            System.out.println("Limita de timp a fost depasita");
            currentGame.setGameContinue(false);
            return false;
        }
        return true;
    }

    private boolean gameContinue(){
        return currentGame.isGameContinue();
    }
}
