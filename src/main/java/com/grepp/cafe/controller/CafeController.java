package com.grepp.cafe.controller;

import com.grepp.cafe.domain.Product;
import com.grepp.cafe.dto.CartItem;
import com.grepp.cafe.dto.CreateOrderDto;
import com.grepp.cafe.dto.OrderForm;
import com.grepp.cafe.dto.OrderItemDto;
import com.grepp.cafe.service.OrderService;
import com.grepp.cafe.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cafe")
public class CafeController {
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public CafeController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping
    public String shopPage(HttpSession session, Model model) {
        List<Product> products = productService.getAllProducts();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        long totalAmount = 0L;
        for (CartItem cartItem : cart) {
            totalAmount += (cartItem.getPrice() * cartItem.getQuantity());
        }

        model.addAttribute("products", products);
        model.addAttribute("cart", cart);
        model.addAttribute("orderForm", new OrderForm());
        model.addAttribute("totalAmount", totalAmount);
        return "cafe";
    }

    @GetMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable UUID productId, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        Product product = productService.getProductById(productId).get();
        CartItem cartItem = cart.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            cart.add(new CartItem(product.getProductId(), product.getProductName(), 1, product.getPrice()));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        session.setAttribute("cart", cart);

        return "redirect:/cafe";
    }

    @PostMapping("/pay")
    public String placeOrder(@ModelAttribute OrderForm orderForm, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cafe";
        }

        CreateOrderDto createOrderDto = new CreateOrderDto(
                orderForm.getEmail(),
                orderForm.getAddress(),
                orderForm.getPostcode(),
                cart.stream()
                        .map(item -> new OrderItemDto(item.getProductName(), item.getQuantity()))
                        .collect(Collectors.toList())
        );

        orderService.createOrder(createOrderDto);

        session.removeAttribute("cart");
        return "redirect:/cafe";
    }
}
