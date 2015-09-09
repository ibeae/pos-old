package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.DBMock;
import ar.com.terminal.dto.Product;
import ar.com.terminal.model.Catalog;
import ar.com.terminal.model.FullProductDescription;
import ar.com.terminal.model.NullProduct;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ivan on 06/09/15.
 */
public class PointOfSaleServiceTest {

    private String id;

    @Test
    public void return_a_product_when_id_is_passed_and_product_exists(){
        id = "78798133540489";

        PointOfSaleService service = getPointOfSaleService();

        Product product = new Product();
        product.setId(id);
        product.setDescription("AVE Malbec 750Ml");
        product.setPrice(70F);

        service.addProduct(product);

        Product existingProduct = service.getProduct(id);

        assertThat(existingProduct, is(notNullValue()));
        assertThat(existingProduct, is(product));
    }

    @Ignore
    public void return_an_empty_product_when_id_is_passed_and_product_does_not_exist(){
        id = "78798133540489";

        PointOfSaleService service = getPointOfSaleService();

        Product existingProduct = service.getProduct(id);

        assertThat(existingProduct, is(notNullValue()));
        assertThat(existingProduct.getId(), is(NullProduct.name));
        assertThat(existingProduct.getDescription(), is(NullProduct.desc));
        assertThat(existingProduct.getPrice(), is(NullProduct.price));
    }

    @Test
    public void return_full_info_from_a_product() throws MalformedURLException {
        id = "78798133540489";
        PointOfSaleService service = getPointOfSaleService();

        FullProductDescription fullProductDescription = service.getFullProductInformation(id);

        assertThat(fullProductDescription, is(notNullValue()));

        String fullDescription = "10 meses en barricas de roble frances de primer uso y, al menos 8 meses en botella." +
                "Es el primer balbec con estilo, sabor y caracter italiano. Rojo intenso, sabor frutado";
        URL pictureURL = new URL("http://localhost:8080/pos/product/" + id + "/info/image.jpg");

        assertThat(fullProductDescription.getFullDescription(), is(fullDescription));
        assertThat(fullProductDescription.getPictureURL(), is(pictureURL));
    }

    private PointOfSaleService getPointOfSaleService() {
        Catalog catalog = new Catalog(new DBMock());
        Controller controller = new Controller(catalog);
        return new PointOfSaleService(controller);
    }
}