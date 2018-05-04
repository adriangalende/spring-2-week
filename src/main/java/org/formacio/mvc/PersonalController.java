package org.formacio.mvc;

import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.Status;
import com.sun.deploy.net.HttpResponse;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.net.StandardProtocolFamily;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.status;


@Controller
@RequestMapping(path="/personal", method=RequestMethod.GET)
@ResponseBody
public class PersonalController {

	// no toqueu la declaracio de baseDeDades ni el metode getBaseDeDades
	// 
	// als metodes que anau afegint, tracteu aquest array com si fos la base de dades
	//
	// per exemple, obtenir la persona amb id 1 sera fer baseDeDades.get(1), etc etc

	
	private List<String> baseDeDades = new ArrayList<>(Arrays.asList("Joana","Antonia","Pere"));
	
	public List<String> getBaseDeDades() {
		return baseDeDades;
	}

	// Poseu a partir d'aqui els vostre metodes
		@RequestMapping(path="/info")
		@ResponseBody
		public String info(){
			return "Hi ha " + getBaseDeDades().size() + " persones";
		}

		@RequestMapping(path = "/consulta")
		@ResponseBody
		public String consulta( @RequestParam(required = false, defaultValue = "0") int id){
			return getBaseDeDades().get(id);
		}

		@RequestMapping(path="/persona/{id}")
		@ResponseBody
		public String consultaPathVariable(@PathVariable  int id){
			return getBaseDeDades().get(id);
		}

		@RequestMapping(path="/afegir", method=RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<String> addAlumno(@RequestParam(name="nom") String nom){
			if (nom.split(",").length > 1){
				return new ResponseEntity<>("error", HttpStatus.UNAUTHORIZED);
			}
			getBaseDeDades().add(nom);
			return new ResponseEntity<>("ok", HttpStatus.OK) ;
		}
}
