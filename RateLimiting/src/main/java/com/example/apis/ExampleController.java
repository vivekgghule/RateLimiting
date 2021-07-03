/**
 * 
 */
package com.example.apis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vivek
 *
 */
@RestController
@RequestMapping("/api")
public class ExampleController {
	
	@GetMapping(path="/greet")
	public ResponseEntity<Object> sampleApi(@RequestParam("tenant") String tenant) {
		return new ResponseEntity<Object>("Hello World..!",HttpStatus.OK);
	}

}
