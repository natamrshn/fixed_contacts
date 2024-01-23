package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.order.orderitem.OrderItemDto;
import spring.boot.optic.okulist.service.order.orderitem.OrderItemService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orders/items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get orderItem by id", description = "Get available orderItem by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OrderItem retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required"),
            @ApiResponse(responseCode = "403", description = "Forbidden. Insufficient privileges"),
            @ApiResponse(responseCode = "404", description = "OrderItem not found")
    })
    public OrderItemDto getOrderItemById(@PathVariable Long id) {
        return orderItemService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/product/{productId}")
    @Operation(summary = "Get orderItem by productId",
            description = "Get available orderItem by productId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OrderItem retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required"),
            @ApiResponse(responseCode = "403", description = "Forbidden. Insufficient privileges"),
            @ApiResponse(responseCode = "404", description = "OrderItem not found")
    })
    public OrderItemDto getByProductId(@PathVariable Long productId) {
        return orderItemService.getByProductId(productId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete orderItem by id",
            description = "Soft delete of orderItem by id from orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "OrderItem deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized. Authentication required"),
            @ApiResponse(responseCode = "403", description = "Forbidden. Insufficient privileges"),
            @ApiResponse(responseCode = "404", description = "OrderItem not found")
    })
    public void deleteById(@PathVariable Long id) {
        orderItemService.deleteById(id);
    }
}
