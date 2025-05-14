package com.shopverse.backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shopverse.backend.dto.CategoryRequestDto;
import com.shopverse.backend.dto.CategoryResponseDto;
import com.shopverse.backend.dto.ProductRequestDto;
import com.shopverse.backend.dto.ProductResponseDto;
import com.shopverse.backend.model.Category;
import com.shopverse.backend.model.Product;
import com.shopverse.backend.repository.CategoryRepository;
import com.shopverse.backend.repository.ProductRepository;



@Service
public class ProductService {
	
	private static final String UPLOAD_DIR=System.getProperty("user.home") + "/Documents/uploads/";
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ResponseEntity<String> saveProduct(ProductRequestDto productRequestDto){
		//create a folder if not exists
		//if folder exists, create a path name where we store the imageFile
		//save the image
		//update the image URL and save the Product
		
		try {
			File uploadDir= new File(UPLOAD_DIR);
			if(!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			//Save the MultipartFile
			MultipartFile imageFile=productRequestDto.getImageFile();
			String originalFileName=StringUtils.cleanPath(imageFile.getOriginalFilename());
			String fileExtension=originalFileName.substring(originalFileName.lastIndexOf("."));
			String uniqueFileName=UUID.randomUUID()+fileExtension;
			
			Path filePath=Paths.get(UPLOAD_DIR+uniqueFileName);
			Files.copy(imageFile.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
			
			//save the URL in db
			String fileUrl="/uploads/"+uniqueFileName;
			
			//creating a product object to save in DB
			
			Product product=new Product();
			
			product.setName(productRequestDto.getName());
			product.setDescription(productRequestDto.getDescription());
			product.setImageURL(fileUrl);
			product.setPrice(productRequestDto.getPrice());
			product.setQuantity(productRequestDto.getQuantity());
			
			
			Optional<Category> category=categoryRepository.findByName(productRequestDto.getCategoryRequestDto().getName());
			// check if category is present
			if(!category.isPresent()) {
				Category category2=new Category();
				category2.setName(productRequestDto.getCategoryRequestDto().getName());
				category2=categoryRepository.save(category2);
				product.setCategory(category2);
				productRepository.save(product);
			}
			else {
				product.setCategory(category.get());
				productRepository.save(product);
			}
		
			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Failed to upload File"+e.getMessage());
		}
		
		
		return ResponseEntity.ok("Product Saved successfully");
	}
	
	@SuppressWarnings("deprecation")
	public ResponseEntity<ProductResponseDto> getProductById(int id){
		
		ProductResponseDto productResponseDto=new ProductResponseDto();
		
		Product product=productRepository.findById(id).orElseThrow();
		productResponseDto.setName(product.getName());
		productResponseDto.setDescription(product.getDescription());
		productResponseDto.setImageFileUrl(product.getImageURL());
		productResponseDto.setPrice(product.getPrice());
		productResponseDto.setQuantity(product.getQuantity());
		CategoryResponseDto categoryResponseDto=new CategoryResponseDto();
		categoryResponseDto.setName(product.getCategory().getName());
		productResponseDto.setCategoryResponseDto(categoryResponseDto);
		return ResponseEntity.ok(productResponseDto);
		
	}

	public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products=productRepository.findAll();
		List<ProductResponseDto> productResponseDtos=new ArrayList<>();
		
		for(Product product:products) {
			ProductResponseDto productResponseDto=new ProductResponseDto();
			productResponseDto.setName(product.getName());
			productResponseDto.setDescription(product.getDescription());
			productResponseDto.setImageFileUrl(product.getImageURL());
			productResponseDto.setPrice(product.getPrice());
			productResponseDto.setQuantity(product.getQuantity());
			CategoryResponseDto categoryResponseDto=new CategoryResponseDto();
			categoryResponseDto.setName(product.getCategory().getName());
			productResponseDto.setCategoryResponseDto(categoryResponseDto);
			productResponseDtos.add(productResponseDto);
		}
		
		return ResponseEntity.ok(productResponseDtos);
	}

	public ResponseEntity<String> updateProductById(int id, ProductRequestDto productRequestDto) throws IOException {
		// TODO Auto-generated method stub
		Optional<Product> productOpt=productRepository.findById(id);
		
		Product product=productOpt.get();
		
		Path path=Paths.get(System.getProperty("user.home") + "/Documents"+product.getImageURL());
		
		Files.delete(path);
		
		File uploadDir= new File(UPLOAD_DIR);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		//Save the MultipartFile
		MultipartFile imageFile=productRequestDto.getImageFile();
		String originalFileName=StringUtils.cleanPath(imageFile.getOriginalFilename());
		String fileExtension=originalFileName.substring(originalFileName.lastIndexOf("."));
		String uniqueFileName=UUID.randomUUID()+fileExtension;
		
		Path filePath=Paths.get(UPLOAD_DIR+uniqueFileName);
		Files.copy(imageFile.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
		
		//save the URL in db
		String fileUrl="/uploads/"+uniqueFileName;
		
		//creating a product object to save in DB
		
		
		product.setName(productRequestDto.getName());
		product.setDescription(productRequestDto.getDescription());
		product.setImageURL(fileUrl);
		product.setPrice(productRequestDto.getPrice());
		product.setQuantity(productRequestDto.getQuantity());
		
		
		Optional<Category> category=categoryRepository.findByName(productRequestDto.getCategoryRequestDto().getName());
		// check if category is present
		if(!category.isPresent()) {
			Category category2=new Category();
			category2.setName(productRequestDto.getCategoryRequestDto().getName());
			categoryRepository.save(category2);
			product.setCategory(category2);
			productRepository.save(product);
		}
		else {
			product.setCategory(category.get());
			productRepository.save(product);
		}
		
		
		return ResponseEntity.ok("Product Saved successfully");
	}


}
