package utils;

public class Paths {
    //BasePage paths
    public static String LOADER = "//div[@class='loader']";

    //CatalogPage paths
    public static String PRODUCT_CATEGORIES = "//ul[@class='product-category-list']/descendant::a";
    public static String PRODUCTS_PER_CATEGORY = "//div[@id='products_by_category']/child::div";
    public static String ADD_BUTTON = ".//descendant::a[@title='Agregar']";

    //CheckOutPage paths
    public static String PURCHASE_MODAL = "//div[@class='modal-content' and descendant::h5[contains(text(),'Compra Realizada')]]";
    public static String MODAL_BUTTON = ".//descendant::button";
    public static String MODAL_TITLE = ".//descendant::h5[contains(text(),'Compra Realizada')]";

    //HomePage paths
    public static String STOCK_SIZE = ".//div[@class='miso-prd-total']";
    public static String ADDED_PRODUCTS = "//div[@id='products-carousel']/child::div[div[@class='miso-prd-qty' and text()>0]]";
    public static String REMOVE_BUTTON = ".//descendant::a[@title='Remover']";
    public static String PRODUCT_COUNT = ".//div[@class='miso-prd-qty']";
    public static String CLEAR_BUTTON = ".//descendant::a[@title='Limpiar']";
    public static String DETAILS_BUTTON = ".//descendant::a[@title='Ver detalle']";
    public static String DETAILS_MODAL = "//div[@class='modal-content' and descendant::div[@id='product-detail-name']]";
    public static String DETAILS_MODAL_TITLE = ".//descendant::h3[parent::div[@id='product-detail-name']]";
    public static String PRODUCT_NAME = ".//descendant::a[parent::h3[@class='title']]";
    public static String CHECKOUT_BUTTON = "//button[parent::div[@class='mini-shopcart-action'] and contains(@class,'btn-checkout')]";

    //LoginPage paths
    public static String LOGIN_MODAL_TEXT = "//div[@class='modal-body' and contains(text(), 'Usuario y/o')]";
    public static String LOGIN_MODAL_BUTTON = "//button[contains(text(), 'Intentar de Nuevo')]";

    //OrdersPage paths
    public static String ORDER_DETAILS_BUTTON = ".//descendant::a[contains(text(),'Ver detalle')]";
    public static String ORDER_NUMBER = ".//descendant::td[1]";
    public static String ORDER_MODAL = "//div[@id='details']";
    public static String ORDER_PRODUCTS_MODAL = ".//descendant::tbody/tr";
    public static String ORDER_PRODUCT_NAME_MODAL = "./td[1]";
    public static String ORDER_PRODUCT_AMOUNT_MODAL = "./td[2]";
    public static String ORDER_BUTTON_MODAL = ".//descendant::button";

    //RegistrationPage paths
}
