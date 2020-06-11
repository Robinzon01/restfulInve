package com.cdsi.backend.inve.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdsi.backend.inve.models.entity.Articulo;
import com.cdsi.backend.inve.models.entity.IdArticulo;
import com.cdsi.backend.inve.models.services.IArticuloService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/arti")
public class ArticuloController {
	
	@Autowired
	private IArticuloService artiServi;
	
	private final Logger log = LoggerFactory.getLogger(ArticuloController.class);
	
	//METODO QUE NOS PERMITE BUSCAR POR ARTICULO
  	@GetMapping("/list/desc/{cia}/{desc}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public List<Articulo> listaArtiDesc(@PathVariable("cia") String cia, @PathVariable("desc") String desc ){
  		return artiServi.likeDescripArti(cia, desc);
  	}
	
	//METODO QUE ENVIA UNA PAGINACION DE ARTICULO y DESCRIPCION
  	@GetMapping("/listd/page/{cia}/{desc}/{page}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public Page<Object> pagiArtiPrecAndDesc(@PathVariable("cia") String cia,@PathVariable("desc") String desc, @PathVariable("page") Integer page ){
  		Pageable pageable = PageRequest.of(page, 8);
  		return artiServi.pagArtiPreStockAndDesc(pageable, cia, "1", desc);
  	}
  	
    //METODO QUE ENVIA UNA PAGINACION DE ARTICULO y CODIGO
  	@GetMapping("/listc/page/{cia}/{cod}/{page}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public Page<Object> pagiArtiPrecAndCodigo(@PathVariable("cia") String cia,@PathVariable("cod") String cod, @PathVariable("page") Integer page ){
  		Pageable pageable = PageRequest.of(page, 8);
  		return artiServi.pagArtiPreStockAndCodigo(pageable, cia, "1", cod);
  	}
  	
     //METODO QUE ENVIA UNA PAGINACION DE ARTICULO
  	@GetMapping("/list/page/{cia}/{page}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public Page<Object> pagiArticulos(@PathVariable("cia") String cia, @PathVariable("page") Integer page ){
  		Pageable pageable = PageRequest.of(page, 8);
  		return artiServi.pagArtiPrecStock(pageable, cia,"1");
  	}
  	
    //METODO QUE NOS DEVUELVE EL PRECIO DEL ARTICULO
  	@GetMapping("/precio/{cia}/{arti}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public String precioArticulo(@PathVariable("cia") String cia, @PathVariable("arti") String arti ){
  		return artiServi.precioArticulo(cia, arti);
  	}
    //METODO QUE NOS DEVUELVE EL STOCK DEL ARTICULO
  	@GetMapping("/stkarti/{cia}/{arti}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public String saldoArticulo(@PathVariable("cia") String cia, @PathVariable("arti") String arti ){
  		return artiServi.saldoArticulo(cia, arti);
  	}
    //METODO QUE NOS DEVUELVE EL STOCK COMPROMETIDO DEL ARTICULO
  	@GetMapping("/comparti/{cia}/{arti}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public String articuloComprometido(@PathVariable("cia") String cia, @PathVariable("arti") String arti ){
  		return artiServi.saldoComprometido(cia, arti);
  	}
  	// SUBIR UNA IMAGEN DEL ARTICULO
  	@PostMapping("/upload")
  	@Secured({"ROLE_ADMIN"})
  	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @Valid @RequestBody IdArticulo idArti ) {
  		Map<String, Object> response = new HashMap<>();
  		Articulo articulo = artiServi.findArticulo(idArti);
  		if(!archivo.isEmpty()) {
  			String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ","");
  			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
  			log.info(rutaArchivo.toString());
  			try {
				Files.copy(archivo.getInputStream(),rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen : "+nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
  			//VAMOS A ELIMINAR LA IMAGEN REPETIDAS
  			String nombreFotoAnterior = articulo.getFoto();
  			if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
  				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
  				File fileFotoAnterior = rutaFotoAnterior.toFile();
  				if(fileFotoAnterior.exists() && fileFotoAnterior.canRead()) {
  					fileFotoAnterior.delete();
  				}
  			}
  			
  			articulo.setFoto(nombreArchivo);
  			artiServi.createArticulo(articulo);
  			
  			response.put("articulo",archivo);
  			response.put("mensaje","Has subido correctamente la imagen : "+nombreArchivo);
  			
  		}
  		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
  	}
  	// METODO QUE NOS PERMITE MOSTRAR LA IMAGEN DE LA FOTO
  	@GetMapping("/upload/img/{nombreFoto:.+}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
  		Path rutaFoto = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
  		log.info(rutaFoto.toString());
  		Resource recurso = null;
  		try {
			recurso = new UrlResource(rutaFoto.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		// VAMOS A VERIFICAR SI SE PUEDE LEER Y EXISTE
  		if(!recurso.exists() && !recurso.isReadable()) {
  			throw new RuntimeException("Error no se puede cargar la imagen: "+nombreFoto);
  		}
  		// VAMOS  DESCARGAR LA IMAGEN DESDE EL SERVIDOR AL CLIENTE
  		HttpHeaders cabecera = new HttpHeaders();
  		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ recurso.getFilename()+"\"");
  		
  		return new ResponseEntity<Resource>(recurso,HttpStatus.OK);
  	}
  	

}
