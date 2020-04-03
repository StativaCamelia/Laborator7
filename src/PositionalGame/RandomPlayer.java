package PositionalGame;

public class RandomPlayer extends Player {

    RandomPlayer(String name, Game game){
        this.name = name;
        this.currentGame = game;
        game.addPlayer(this);
    }

    @Override
    public void run() {
        try {
            while (currentGame.isGameContinue()) {
                Thread.sleep(4000);

                if (!currentGame.isGameContinue()) {
                    Thread.currentThread().interrupt();
                    return;
                }

                if (currentGame.gameBoard.getTokenList().isEmpty()) {
                    currentGame.setGameContinue(false);
                    Thread.currentThread().interrupt();
                    return;
                } else {
                    int randomPosition = (int) Math.floor(Math.random() * currentGame.getGameBoard().tokenList.size());
                    Token extractedToken = currentGame.extractFromBoard(randomPosition);
                    System.out.println("Jucatorul:" + this.name + " a extras token-ul:" + extractedToken + " ");
                    tokenList.add(extractedToken);

                    if (tokenList.size() >= currentGame.getProgressionSize()) {
                        if (this.lenghtOfLongestProgression() >= currentGame.getProgressionSize()) {
                            System.out.println("Jucatorul" + name + "este castigator");
                            currentGame.setGameContinue(false);
                            score = this.currentGame.gameBoardSize;
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (score != this.currentGame.gameBoardSize) {
                score += lenghtOfLongestProgression();
            }
        }
    }
}
