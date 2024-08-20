package com.app.Service;

import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.customexception.ApiException;
import com.app.customexception.ResourceNotFoundException;
import com.app.Dao.ProductDao;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.ElectronicsDto;
import com.app.dto.FurnitureDto;
import com.app.dto.VehicleDto;
import com.app.entity.Book;
import com.app.entity.Product;

@Service
@Transactional
public class ImageHandlingServiceImpl implements ImageHandlingService {
	// injecting value of the field read from applicatoin.properties file
	@Value("${file.upload.location}") // field level DI , <property name n value />
	// ${file.upload.location} SpEL :Spring expr language
	private String uploadFolder;

	@Autowired
	private ProductDao productDao;

	@Autowired 
	private BookService bookService;
	
	@Autowired
	private ElectronicsService electronicsService;
	
	@Autowired
	private FurnitureService furnitureService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@PostConstruct
	public void init() throws IOException {
		// chk if folder exists --yes --continue
		File folder = new File(uploadFolder);
		if (folder.exists()) {
			System.out.println("folder exists alrdy !");
		} else {
			// no --create a folder
			folder.mkdir();
			System.out.println("created a folder !");
		}
		System.out.println(folder.getAbsolutePath());
	}

	@Override
	public ApiResponse uploadImage(Long Id, MultipartFile[] images) throws IOException {
		// get emp from emp id
		Product product =productDao.
				findById(Id).orElseThrow(() -> new ResourceNotFoundException("Invalid product ID!!!!"));
		// emp found --> PERSISTENT
		// store the image on server side folder
		String path = uploadFolder.concat(images[0].getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), images[0].getBytes());
		// set image path in DB (emps table)
		product.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		
		String path1 = uploadFolder.concat(images[1].getOriginalFilename());
		System.out.println(path1);
		writeByteArrayToFile(new File(path1), images[1].getBytes());
		product.setImagePath1(path1);
		
		String path2 = uploadFolder.concat(images[2].getOriginalFilename());
		System.out.println(path2);
		writeByteArrayToFile(new File(path2), images[2].getBytes());
		product.setImagePath2(path2);
		
		String path3 = uploadFolder.concat(images[3].getOriginalFilename());
		System.out.println(path3);
		writeByteArrayToFile(new File(path3), images[3].getBytes());
		product.setImagePath3(path3);
		
		
		
		return new ApiResponse("Image file uploaded successfully for emp id " + Id);
	}

	@Override
	public String uploadImage(BookDto bookDto, MultipartFile[] images) throws IOException {
		// store the image on server side folder
		String path = uploadFolder.concat(images[0].getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), images[0].getBytes());
		// set image path
		bookDto.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		
		String path1 = uploadFolder.concat(images[1].getOriginalFilename());
		System.out.println(path1);
		writeByteArrayToFile(new File(path1), images[1].getBytes());
		bookDto.setImagePath1(path1);
		
		String path2 = uploadFolder.concat(images[2].getOriginalFilename());
		System.out.println(path2);
		writeByteArrayToFile(new File(path2), images[2].getBytes());
		bookDto.setImagePath2(path2);
		
		String path3 = uploadFolder.concat(images[3].getOriginalFilename());
		System.out.println(path3);
		writeByteArrayToFile(new File(path3), images[3].getBytes());
		bookDto.setImagePath3(path3);
	
	   
	   bookService.addBook(bookDto);
		
		
		 return "Images file uploaded successfully for product";
	}
	
	@Override
	public String uploadImage(ElectronicsDto electronicsDto, MultipartFile[] images) throws IOException {
		// store the image on server side folder
		String path = uploadFolder.concat(images[0].getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), images[0].getBytes());
		// set image path
		electronicsDto.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		
		String path1 = uploadFolder.concat(images[1].getOriginalFilename());
		System.out.println(path1);
		writeByteArrayToFile(new File(path1), images[1].getBytes());
		electronicsDto.setImagePath1(path1);
		
		String path2 = uploadFolder.concat(images[2].getOriginalFilename());
		System.out.println(path2);
		writeByteArrayToFile(new File(path2), images[2].getBytes());
		electronicsDto.setImagePath2(path2);
		
		String path3 = uploadFolder.concat(images[3].getOriginalFilename());
		System.out.println(path3);
		writeByteArrayToFile(new File(path3), images[3].getBytes());
		electronicsDto.setImagePath3(path3);
	
	   
	   electronicsService.addElectronics(electronicsDto);
		
		
		 return "Images file uploaded successfully for product";
	}
	
	@Override
	public String uploadImage(FurnitureDto furnitureDto, MultipartFile[] images) throws IOException {
		// store the image on server side folder
		String path = uploadFolder.concat(images[0].getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), images[0].getBytes());
		// set image path
		furnitureDto.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		
		String path1 = uploadFolder.concat(images[1].getOriginalFilename());
		System.out.println(path1);
		writeByteArrayToFile(new File(path1), images[1].getBytes());
		furnitureDto.setImagePath1(path1);
		
		String path2 = uploadFolder.concat(images[2].getOriginalFilename());
		System.out.println(path2);
		writeByteArrayToFile(new File(path2), images[2].getBytes());
		furnitureDto.setImagePath2(path2);
		
		String path3 = uploadFolder.concat(images[3].getOriginalFilename());
		System.out.println(path3);
		writeByteArrayToFile(new File(path3), images[3].getBytes());
		furnitureDto.setImagePath3(path3);
	
	   
	   furnitureService.addFurniture(furnitureDto);
		
		
		 return "Images file uploaded successfully for product";
	}
	
	@Override
	public String uploadImage(VehicleDto vehicleDto, MultipartFile[] images) throws IOException {
		// store the image on server side folder
		String path = uploadFolder.concat(images[0].getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), images[0].getBytes());
		// set image path
		vehicleDto.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		
		String path1 = uploadFolder.concat(images[1].getOriginalFilename());
		System.out.println(path1);
		writeByteArrayToFile(new File(path1), images[1].getBytes());
		vehicleDto.setImagePath1(path1);
		
		String path2 = uploadFolder.concat(images[2].getOriginalFilename());
		System.out.println(path2);
		writeByteArrayToFile(new File(path2), images[2].getBytes());
		vehicleDto.setImagePath2(path2);
		
		String path3 = uploadFolder.concat(images[3].getOriginalFilename());
		System.out.println(path3);
		writeByteArrayToFile(new File(path3), images[3].getBytes());
		vehicleDto.setImagePath3(path3);
	
	   
	   vehicleService.addVehicle(vehicleDto);
		
		
		 return "Images file uploaded successfully for product";
	}

	@Override
	public byte[] serveImage(Long empId) throws IOException {
		// get emp by id
		Product product = productDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		String path = product.getImagePath();
		if (path != null) {
			// path ---> File --> byte[]
			return readFileToByteArray(new File(path));
			// OR from DB : return emp.getImage();
		} else
			throw new ApiException("Image not yet assigned !!!!");

	}

}

