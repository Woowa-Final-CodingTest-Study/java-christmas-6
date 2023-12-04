package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void 실패__메뉴_리스트에_없는_메뉴를_불러올_경우() {
        Menu.init();
        Assertions.assertThatThrownBy(() -> Menu.get("없는메뉴"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 성공__메뉴_리스트에_있는_메뉴를_불러올_경우() {
        Menu.init();
        Assertions.assertThatNoException()
                .isThrownBy(() -> Menu.get("제로콜라"));
    }
}
