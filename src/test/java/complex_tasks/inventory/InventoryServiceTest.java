package complex_tasks.inventory;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {
    /**
     * Тесты сервиса управления складом:
     * positive cases:
     *  Добавление валидного товара:
     *          категории еще нет на складе: addProduct(Product("Apple", 100.0, "Food")) → товар добавлен в только созданную категорию "Food"
     *          категория уже существует: addProduct(Product(apple), Product(orange)) → товар orange добавлен в уже существующую категорию "Food"
     *  Получение товара по категории:
     *      addProduct(Product("Apple", 100.0, "Food"))
     *          getProductByCategory("Food") → Product("Apple", 100.0, "Food")
     *  Получение товара уменьшает количество товаров на складе:
     *      addProduct(Product("Apple", 100.0, "Food"))
     *          getProductByCategory("Food") → assertEquals(0, actualResult.size())
     *  Фильтрация товаров по категории:
     *      addProduct(Product("Apple", 100.0, "Food"))
     *      addProduct(Product("Orange", 50.0, "Food"))
     *      addProduct(Product("Phone", 1000.0, "Tech"))
     *          filterByCategory("Food") → Apple, Bread
     *  Фильтрация товаров по диапазону цены. Товары есть в выбранном диапазоне цен:
     *      addProduct(Product("Apple", 100.0, "Food"))
     *      addProduct(Product("Orange", 50.0, "Food"))
     *      addProduct(Product("Phone", 1000.0, "Tech"))
     *          filterByPrice(50.0, 100.0) → Apple, Bread
     *  Фильтрация товаров по диапазону цены. Товаров нет в выбранном диапазоне цен:
     *      addProduct(Product("Apple", 100.0, "Food"))
     *          filterByPrice(50.0, 80.0) → assertEquals(0, actualResult.size())
     * corner cases:
     *  Добавление товара. Цена равна 0:
     *          addProduct(Product("Gift", 0.0, "Promo")) → товар добавлен
     *  Фильтрация товаров. Минимальная и максимальная цена равны:
     *      addProduct(Product("Apple", 100.0, "Food"))
     *      addProduct(Product("Orange", 50.0, "Food"))
     *          filterByPrice(50.0, 50.0) → Orange
     *  Фильтрация товаров по отсутствующей категории:
     *      addProduct(Product("Apple", 100.0, "Food"))
     *          filterByCategory("Tech") → assertEquals(0, actualResult.size())
     *  Добавление товара при закрытом складе:
     *      setInventoryOpen(false)
     *          addProduct(Product("Apple", 100.0, "Food")) → товар не добавлен, assertEquals(0, actualResult.size())
     * negative cases:
     *  Добавление товара. Product равен null:
     *          addProduct(null) → IllegalArgumentException
     *  Добавление товара. Название товара равно null:
     *          addProduct(Product(null, 100.0, "Food")) → IllegalArgumentException
     *  Добавление товара. Категория равна null:
     *          addProduct(Product("Apple", 100.0, null)) → IllegalArgumentException
     *  Добавление товара. Цена отрицательная:
     *          addProduct(Product("Apple", -1.0, "Food")) → IllegalArgumentException
     *  Получение товара. Категория равна null:
     *          getProductByCategory(null) → IllegalArgumentException
     *  Получение товара. Категории нет на складе:
     *          getProductByCategory("Food") → OutOfStockException
     *  Получение товара. Категория есть, но товары закончились:
     *      addProduct(Product("Apple", 100.0, "Food"))
     *      getProductByCategory("Food")
     *          getProductByCategory("Food") → OutOfStockException
     *  Фильтрация по категории. Категория равна null
     *          filterByCategory(null) → IllegalArgumentException
     *  Фильтрация по цене. Минимальная цена отрицательная:
     *          filterByPrice(-1.0, 100.0) → IllegalArgumentException
     *  Фильтрация по цене. Максимальная цена отрицательная:
     *          filterByPrice(0.0, -100.0) → IllegalArgumentException
     *  Фильтрация по цене. Минимальная цена больше максимальной:
     *          filterByPrice(100.0, 50.0) → IllegalArgumentException
     */

    /*addProduct() - добавление товара на склад*/

    @Test
    public void shouldAddValidProductToEmptyInventory() throws OutOfStockException {
        // Добавление валидного товара. Категории еще нет на складе
        InventoryService inventoryService = new InventoryService();

        Product product = new Product("Apple", 100.0, "Food");

        inventoryService.addProduct(product);

        List<Product> actualResult = inventoryService.filterByCategory("Food");

        assertEquals(1, actualResult.size());

        Product actualProduct = actualResult.getFirst();

        assertEquals("Apple", actualProduct.getName());
        assertEquals(100.0, actualProduct.getPrice());
        assertEquals("Food", actualProduct.getCategory());
    }

    @Test
    public void shouldAddValidProductToExistingCategory() throws OutOfStockException {
        // Добавление валидного товара. Категория уже существует
        InventoryService inventoryService = new InventoryService();

        Product apple = new Product("Apple", 100.0, "Food");
        Product orange = new Product("Orange", 100.0, "Food");

        inventoryService.addProduct(apple);
        inventoryService.addProduct(orange);

        List<Product> actualResult = inventoryService.filterByCategory("Food");

        assertEquals(2, actualResult.size());
    }

    @Test
    public void shouldAddProductWithZeroPrice() {
        // Добавление товара. Цена равна 0
        InventoryService inventoryService = new InventoryService();

        Product product = new Product("Gift", 0.0, "Promo");

        inventoryService.addProduct(product);

        List<Product> actualResult = inventoryService.filterByCategory("Promo");

        assertEquals(1, actualResult.size());

        Product actualProduct = actualResult.getFirst();

        assertEquals("Gift", actualProduct.getName());
        assertEquals(0.0, actualProduct.getPrice());
        assertEquals("Promo", actualProduct.getCategory());
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenAddingProductToClosedInventory() {
        // Добавление товара при закрытом складе
        InventoryService inventoryService = new InventoryService();

        inventoryService.setInventoryOpen(false);
        inventoryService.addProduct(new Product("Apple", 100.0, "Food"));

        List<Product> actualResult = inventoryService.filterByCategory("Food");

        assertEquals(0, actualResult.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullProduct() {
        // Добавление товара. Product равен null
        InventoryService inventoryService = new InventoryService();

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.addProduct(null)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullProductName() {
        // Добавление товара. Название товара равно null
        InventoryService inventoryService = new InventoryService();

        Product product = new Product(null, 0.0, "Promo");

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.addProduct(product)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullCategory() {
        // Добавление товара. Категория равна null
        InventoryService inventoryService = new InventoryService();

        Product product = new Product("Apple", 100.0, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.addProduct(product)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNegativePrice() {
        // Добавление товара. Цена отрицательная
        InventoryService inventoryService = new InventoryService();

        Product product = new Product("Apple", -1.0, "Food");

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.addProduct(product)
        );
    }

    /*getProductByCategory() - получение товара по категории*/

    @Test
    public void shouldGetProductByCategory() throws OutOfStockException {
        // Получение товара по категории
        InventoryService inventoryService = new InventoryService();

        inventoryService.addProduct(new Product("Apple", 100.0, "Food"));

        Product actualResult = inventoryService.getProductByCategory("Food");

        assertEquals("Apple", actualResult.getName());
        assertEquals(100.0, actualResult.getPrice());
        assertEquals("Food", actualResult.getCategory());
    }

    @Test
    public void shouldThrowOutOfStockExceptionWhenCategoryBecomesEmpty() throws OutOfStockException {
        // Получение товара уменьшает количество товаров на складе
        // Получение товара. Категория есть, но товары закончились
        InventoryService inventoryService = new InventoryService();

        inventoryService.addProduct(new Product("Apple", 100.0, "Food"));

        inventoryService.getProductByCategory("Food");

        List<Product> actualResult = inventoryService.filterByCategory("Food");

        assertEquals(0, actualResult.size());

        assertThrows(
                OutOfStockException.class,
                () -> inventoryService.getProductByCategory("Food")
        );
    }

    @Test
    public void shouldThrowOutOfStockExceptionForNullCategory() {
        // Получение товара. Категория равна null
        InventoryService inventoryService = new InventoryService();

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.getProductByCategory(null)
        );
    }

    @Test
    public void shouldThrowOutOfStockExceptionForNonExistingCategory() {
        // Получение товара. Категории нет на складе
        InventoryService inventoryService = new InventoryService();

        assertThrows(
                OutOfStockException.class,
                () -> inventoryService.getProductByCategory("Food")
        );
    }

    /*filterByPrice() - фильтрация товаров по цене*/

    @Test
    public void shouldFilterProductsByPrice() {
        // Фильтрация товаров по диапазону цен
        InventoryService inventoryService = new InventoryService();

        inventoryService.addProduct(new Product("Apple", 100.0, "Food"));
        inventoryService.addProduct(new Product("Orange", 50.0, "Food"));
        inventoryService.addProduct(new Product("Phone", 1000.0, "Tech"));

        List<Product> actualResult = inventoryService.filterByPrice(50.0, 100.0);

        assertEquals(2, actualResult.size());
    }

    @Test
    public void shouldReturnEmptyListWhenNoProductsMatchPriceRange() {
        // Фильтрация товаров по диапазону цены. Товаров нет в выбранном диапазоне цен
        InventoryService inventoryService = new InventoryService();

        inventoryService.addProduct(new Product("Apple", 100.0, "Food"));

        List<Product> actualResult = inventoryService.filterByPrice(50.0, 80.0);

        assertEquals(0, actualResult.size());
    }

    @Test
    public void shouldFilterProductsWhenMinimumAndMaximumPricesAreEqual() {
        // Фильтрация товаров. Минимальная и максимальная цена равны
        InventoryService inventoryService = new InventoryService();

        inventoryService.addProduct(new Product("Apple", 100.0, "Food"));
        inventoryService.addProduct(new Product("Orange", 50.0, "Food"));

        List<Product> actualResult = inventoryService.filterByPrice(50.0, 50.0);

        assertEquals(1, actualResult.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNegativeMinimumPrice() {
        //Фильтрация по цене. Минимальная цена отрицательная
        InventoryService inventoryService = new InventoryService();

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.filterByPrice(-1.0, 100.0)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNegativeMaximumPrice() {
        // Фильтрация по цене. Максимальная цена отрицательная
        InventoryService inventoryService = new InventoryService();

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.filterByPrice(0.0, -100.0)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenMinimumPriceExceedsMaximumPrice() {
        // Фильтрация по цене. Минимальная цена больше максимальной
        InventoryService inventoryService = new InventoryService();

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.filterByPrice(100.0, 50.0)
        );
    }

    /*filterByCategory() - фильтрация товаров по категории*/

    @Test
    public void shouldFilterProductsByCategory() {
        // Фильтрация товаров по категории
        InventoryService inventoryService = new InventoryService();

        inventoryService.addProduct(new Product("Apple", 100.0, "Food"));
        inventoryService.addProduct(new Product("Orange", 50.0, "Food"));
        inventoryService.addProduct(new Product("Phone", 1000.0, "Tech"));

        List<Product> actualResult = inventoryService.filterByCategory("Food");

        assertEquals(2, actualResult.size());
    }

    @Test
    public void shouldReturnEmptyListForNonExistingCategory() {
        // Фильтрация товаров по отсутствующей категории
        InventoryService inventoryService = new InventoryService();

        inventoryService.addProduct(new Product("Apple", 100.0, "Food"));

        List<Product> actualResult = inventoryService.filterByCategory("Tech");

        assertEquals(0, actualResult.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFilteringByNullCategory() {
        // Фильтрация по категории. Категория равна null
        InventoryService inventoryService = new InventoryService();

        assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.filterByCategory(null)
        );
    }
}
