package org.springframework.samples.petclinic.owner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OwnerApiController {

	private static final Logger log = LoggerFactory.getLogger(OwnerApiController.class);

	private final OwnerRepository owners;

	public OwnerApiController(final OwnerRepository owners) {
		this.owners = owners;
	}

	@CrossOrigin(origins = { "*" })
	@GetMapping("/v1/api/owners/{ownerId}")
	public Owner getOwner(@PathVariable String ownerId) {

		if (Optional.ofNullable(ownerId).isEmpty()) {
			return new Owner();
		}

		log.info("Getting owner with id: {}", ownerId);
		return this.owners.findById(Integer.parseInt(ownerId))
			.orElseThrow(() -> new IllegalArgumentException("Owner not found with id: " + ownerId));
	}

}
