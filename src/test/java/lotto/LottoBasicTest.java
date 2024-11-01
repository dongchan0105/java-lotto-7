package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.Controller.InputController;
import lotto.Controller.PlayController;
import lotto.Model.LottoNumbers;
import lotto.Model.PlayLottoGame;
import lotto.Model.Ranking;
import lotto.Model.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoBasicTest {

    private Validation validation;
    private InputController inputController;
    private PlayController playController;


    void setUp() {
        validation = new Validation();
        inputController = new InputController();
        playController = new PlayController();
    }

    @Test
    void testPurchaseValidator() {
        assertThatThrownBy(() -> validation.purchaseValidator(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원단위로만 구매할 수 있습니다");

        assertThatThrownBy(() -> validation.purchaseValidator(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원단위로만 구매할 수 있습니다");
    }

    @Test
    void testWinningNumberValidator() {
        String[] validNumbers = {"1", "2", "3", "4", "5", "6"};
        assertThatThrownBy(() -> validation.winningNumberValidator(validNumbers));
        validation.winningNumberValidator(validNumbers);

        String[] duplicateNumbers = {"1", "2", "3", "4", "5", "5"};
        assertThatThrownBy(() -> validation.winningNumberValidator(duplicateNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 번호가 있습니다.");

        String[] outOfRangeNumbers = {"1", "2", "3", "4", "5", "46"};
        assertThatThrownBy(() -> validation.winningNumberValidator(outOfRangeNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1에서 45 사이의 숫자여야 합니다.");
    }

    @Test
    void testBonusNumberValidator() {

        validation.bonusNumberValidator(10);  // 정상 입력
        assertThatThrownBy(() -> validation.bonusNumberValidator(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1에서 45 사이의 숫자여야 합니다.");
    }

    @Test
    void testLottoNumbersGenerate() {
        List<Integer> lottoNumbers = LottoNumbers.generateLottoNumbers();
        assertThat(lottoNumbers).hasSize(6);
        assertThat(lottoNumbers).allMatch(num -> num >= 1 && num <= 45);
    }

    @Test
    void testPlayLottoGame() {
        ArrayList<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        List<Lotto> lottoList = LottoNumbers.makeLottoList(1);
        PlayLottoGame playLottoGame = new PlayLottoGame(winningNumbers, lottoList, 7);

        Map<Integer, Boolean> matchingResults = playLottoGame.play();
        assertThat(matchingResults).isNotNull();
    }

    @Test
    void testRankingEnum() {
        assertThat(Ranking.valueOf(6, false)).isEqualTo(Ranking.FIRST);
        assertThat(Ranking.valueOf(5, true)).isEqualTo(Ranking.SECOND);
        assertThat(Ranking.valueOf(5, false)).isEqualTo(Ranking.THIRD);
        assertThat(Ranking.valueOf(4, false)).isEqualTo(Ranking.FOURTH);
        assertThat(Ranking.valueOf(3, false)).isEqualTo(Ranking.FIFTH);
        assertThat(Ranking.valueOf(2, false)).isEqualTo(Ranking.MISS);
    }

    @Test
    void testPlayControllerPurchasePrice() {
        int purchasePrice = inputController.setPurchasePrice();
        assertThat(purchasePrice).isGreaterThan(0);
    }

    @Test
    void testPlayControllerWinningNumbers() {
        ArrayList<Integer> winningNumbers = inputController.setWinningNumber();
        assertThat(winningNumbers).hasSize(6);
        assertThat(winningNumbers).allMatch(num -> num >= 1 && num <= 45);
    }
}
