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

import com.shopverse.backend.dto.ProductDTO;
import com.shopverse.backend.dto.ProductResponse;
import com.shopverse.backend.model.Product;
import com.shopverse.backend.repository.ProductRepository;



@Service
public class ProductService {
	
	private static final String UPLOAD_DIR=System.getProperty("user.home") + "/Documents/uploads/";
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<String> saveProduct(ProductDTO productDto){
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
			MultipartFile imageFile=productDto.getImageFile();
			String originalFileName=StringUtils.cleanPath(imageFile.getOriginalFilename());
			String fileExtension=originalFileName.substring(originalFileName.lastIndexOf("."));
			String uniqueFileName=UUID.randomUUID()+fileExtension;
			
			Path filePath=Paths.get(UPLOAD_DIR+uniqueFileName);
			Files.copy(imageFile.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
			
			//save the URL in db
			String fileUrl="/uploads/"+uniqueFileName;
			
			//creating a product object to save in DB
			
			Product product=new Product();
			
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setImageURL(fileUrl);
			product.setPrice(productDto.getPrice());
			product.setQuantity(product.getQuantity());
			
			productRepository.save(product);
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Failed to upload File"+e.getMessage());
		}
		
		
		return ResponseEntity.ok("Product Saved successfully");
	}
	
	@SuppressWarnings("deprecation")
	public ResponseEntity<ProductResponse> getProductById(int id){
		
		ProductResponse productResponse=new ProductResponse();
		
		Product product=productRepository.getReferenceById(id);
		productResponse.setName(product.getName());
		productResponse.setDescription(product.getDescription());
		productResponse.setImageFileUrl(product.getImageURL());
		product.setPrice(product.getPrice());
		product.setQuantity(product.getQuantity());
		product.setProductId(product.getProductId());
		
		return ResponseEntity.ok(productResponse);
		
	}

	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products=productRepository.findAll();
		List<ProductResponse> productResponses=new ArrayList<>();
		
		for(Product product:products) {
			ProductResponse productResponse=new ProductResponse();
			productResponse.setName(product.getName());
			productResponse.setDescription(product.getDescription());
			productResponse.setImageFileUrl(product.getImageURL());
			product.setPrice(product.getPrice());
			product.setQuantity(product.getQuantity());
			product.setProductId(product.getProductId());
			productResponses.add(productResponse);
			
		}
		
		return ResponseEntity.ok(productResponses);
	}

	public ResponseEntity<String> updateProductById(int id, ProductDTO productDto) throws IOException {
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
		MultipartFile imageFile=productDto.getImageFile();
		String originalFileName=StringUtils.cleanPath(imageFile.getOriginalFilename());
		String fileExtension=originalFileName.substring(originalFileName.lastIndexOf("."));
		String uniqueFileName=UUID.randomUUID()+fileExtension;
		
		Path filePath=Paths.get(UPLOAD_DIR+uniqueFileName);
		Files.copy(imageFile.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
		
		//save the URL in db
		String fileUrl="/uploads/"+uniqueFileName;
		
		//creating a product object to save in DB
		
		
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setImageURL(fileUrl);
		product.setPrice(productDto.getPrice());
		product.setQuantity(product.getQuantity());
		
		productRepository.save(product);
		
		
		return ResponseEntity.ok("Product Saved successfully");
	}


}
