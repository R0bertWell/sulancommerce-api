package src.sulancommerce.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.*;
import src.sulancommerce.models.entities.Cart;
import src.sulancommerce.models.entities.Product;
import src.sulancommerce.models.entities.ProductCart;
import src.sulancommerce.repositories.CartRepository;
import src.sulancommerce.repositories.ProductCartRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final ProductCartRepository productCartRepository;

    public OrderServiceImpl(CartRepository cartRepository,
                            ProductCartRepository productCartRepository) {
        this.cartRepository = cartRepository;
        this.productCartRepository = productCartRepository;
    }

    @Override
    public CartDTO saveOrder(CartDTO cartToSave) {
        Cart cart = cartToSave.toEntity();

        cart = cartRepository.save(cart);

        List<ProductCart> productCart = this.generateProductsCart(cart, cartToSave.getItems());
        productCart = this.productCartRepository.saveAll(productCart);

        return new CartDTO(cart);
    }

    @Override
    public CartDTO updateOrder(CartDTO cartToUpdate, List<ProductCartDTO> productCartsToAdd, List<ProductCartDTO> productCartsToRemove) {
        Cart cart = cartToUpdate.toEntity();

        cart = cartRepository.save(cart);
        /* TODO : Add column Updated Hour */


        List<ProductCart> productCart = this.generateProductsCart(cart, cartToUpdate.getItems());
        productCart = this.productCartRepository.saveAll(productCart);

        return new CartDTO(cart);    }

    @Override
    public Page<CartDTO> getPagedOrders(Pageable pageable) {
        Page<Cart> cartPage = this.cartRepository.getCartsPaged(pageable);

        List<CartDTO> cartDTOS = new ArrayList<>();

        cartPage.getContent().forEach( cart -> {
            CartDTO cartDTO = new CartDTO(cart);
            List<ProductCart> productCarts = this.productCartRepository.getProductsCartByCartId(cart.getCartId());
            List<ProductInfoDTO> productInfoDTOS = new ArrayList<>();
            productCarts.forEach(prodCart -> {
                ProductInfoDTO productInfoDTO = new ProductInfoDTO();
                productInfoDTO.setQuantity(prodCart.getProductQuantity());
                productInfoDTO.setProduct(new ProductDTO(prodCart.getProduct()));
                productInfoDTO.setColor(new ColorDTO(prodCart.getColor()));
                productInfoDTO.setSize(new SizeDTO(prodCart.getSize()));
                productInfoDTOS.add(productInfoDTO);
            });
            cartDTO.setItems(productInfoDTOS);
            cartDTOS.add(cartDTO);
        });

        return new PageImpl<>(cartDTOS, pageable, cartPage.getTotalElements());
    }

    @Override
    public void updatePayedOrder(Long orderId, boolean payed) throws Exception {
        Optional<Cart> cartOptional = this.cartRepository.findById(orderId);

        if(cartOptional.isEmpty())
            throw new Exception("Esse pedido não existe mais ou teve algum erro na consulta no banco.");

        Cart cart = cartOptional.get();
        cart.setPayed(payed);
        cart.setPayedDate(payed ? new Date() : null);
        this.cartRepository.save(cart);
    }

    @Override
    public void updateSentOrder(Long orderId, boolean sent) throws Exception {
        Optional<Cart> cartOptional = this.cartRepository.findById(orderId);

        if(cartOptional.isEmpty())
            throw new Exception("Esse pedido não existe mais ou teve algum erro na consulta no banco.");

        Cart cart = cartOptional.get();
        cart.setSent(sent);
        cart.setSentDate(sent ? new Date() : null);
        this.cartRepository.save(cart);
    }

    private List<ProductCart> generateProductsCart( Cart cart, List<ProductInfoDTO> items){
        List<ProductCart> productCarts = new ArrayList<>();
        for(ProductInfoDTO prodInfo : items){
            ProductCart productCart = new ProductCart();
            Product product = prodInfo.getProduct().toEntity();
            productCart.setCart(cart);
            productCart.setProduct(product);
            productCart.setColor(prodInfo.getColor().toEntity());
            productCart.setSize(prodInfo.getSize().toEntity());
            productCart.setTotalValue(BigDecimal.valueOf(product.getProductValue() * prodInfo.getQuantity()));
            productCart.setProductQuantity(prodInfo.getQuantity());
            productCarts.add(productCart);
        }

        return productCarts;
    }
}
