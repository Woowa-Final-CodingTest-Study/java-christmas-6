package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class MenuCategoryTest {

    @DisplayName("해당 메뉴가 어떤 메뉴 카테고리에 포함되는지 반환")
    @ParameterizedTest
    @CsvSource({"타파스, APPETIZER", "티본스테이크, MAIN", "초코케이크, DESSERT", "제로콜라, BEVERAGE"})
    void checkMenuCategory(String menu, String menuCategory) {
        String result = MenuCategory.findByMenuCategory(menu).name();
        assertThat(result).isEqualTo(menuCategory);
    }

    @DisplayName("카테고리에 해당하는 메뉴 리스트 반환")
    @Test
    void checkMenuCategoryContainMenu() {
        List<String> result = MenuCategory.getMenuInCategory(MenuCategory.BEVERAGE);
        List<String> answer = new ArrayList<>();
        answer.add("제로콜라");
        answer.add("레드와인");
        answer.add("샴페인");

        assertThat(result).isEqualTo(answer);
    }

}