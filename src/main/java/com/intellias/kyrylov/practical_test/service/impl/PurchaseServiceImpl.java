package com.intellias.kyrylov.practical_test.service.impl;

        import com.intellias.kyrylov.practical_test.dto.PurchaseDTO;
        import com.intellias.kyrylov.practical_test.dto.UserDTO;
        import com.intellias.kyrylov.practical_test.exception.NotEnoughMoneyException;
        import com.intellias.kyrylov.practical_test.model.Product;
        import com.intellias.kyrylov.practical_test.model.Purchase;
        import com.intellias.kyrylov.practical_test.model.User;
        import com.intellias.kyrylov.practical_test.repository.ProductRepository;
        import com.intellias.kyrylov.practical_test.repository.PurchaseRepository;
        import com.intellias.kyrylov.practical_test.repository.UserRepository;
        import com.intellias.kyrylov.practical_test.service.PurchaseService;
        import com.intellias.kyrylov.practical_test.util.Mapper;
        import org.modelmapper.ModelMapper;
        import org.modelmapper.PropertyMap;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.math.BigDecimal;
        import java.util.Collection;

@Service
public class PurchaseServiceImpl implements PurchaseService, Mapper<Purchase, PurchaseDTO> {

    private final PurchaseRepository purchaseRepository;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
                               ModelMapper modelMapper,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PurchaseDTO makePurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = new Purchase();
        User user = userRepository
                .findById(purchaseDTO.getUserId())
                .orElseThrow();
        Product product = productRepository
                .findById(purchaseDTO.getProductId())
                .orElseThrow();
        BigDecimal amountOfMoney = user.getAmountOfMoney();
        BigDecimal price = product.getPrice();
        if (amountOfMoney.compareTo(price) >= 0) {
            user.setAmountOfMoney(amountOfMoney.subtract(price));
            purchase.setProduct(product);
            purchase.setUser(user);
            Purchase savedPurchase = purchaseRepository.save(purchase);
            return convertToDTO(savedPurchase);
        }
        throw new NotEnoughMoneyException("Not enough money");
    }

    @Override
    public PurchaseDTO convertToDTO(Purchase purchase) {
        return modelMapper.map(purchase, PurchaseDTO.class);
    }

    @Override
    public Purchase convertToEntity(PurchaseDTO purchaseDTO) {
        modelMapper.map(purchaseDTO, Purchase.class);
        return modelMapper.map(purchaseDTO, Purchase.class);

    }

    @Override
    public Collection<PurchaseDTO> convertAllToDTO(Collection<Purchase> purchases) {
        return purchases.stream().map(this::convertToDTO).toList();
    }

    @Override
    public Collection<Purchase> convertAllToEntity(Collection<PurchaseDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).toList();
    }

}
