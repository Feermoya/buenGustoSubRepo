package com.utn.app.buenGusto.cliente; 
 
import java.util.List;
import org.springframework.stereotype.Service;
import com.utn.app.buenGusto.common.CommonService; 

@Service
public class ClienteService extends CommonService<ClienteEntity, ClienteRepository> {
	
	public ClienteEntity findByUidFirebase(String uid) throws Exception {

		ClienteEntity entityOptional = repository.findByUidFirebase(uid);
		
		try {
			if (entityOptional != null) {
				return entityOptional;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}
	 
	public List<ClienteEntity> findByNombre(String nombre, String apellido) throws Exception {

		List<ClienteEntity> entityOptional = repository.findByName(nombre, apellido);
		
		try {
			if (entityOptional != null) {
				return entityOptional;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}
	
	@Override
	public Iterable<ClienteEntity> findAllByHabilitado(boolean habilitado) throws Exception{
		List<ClienteEntity> entityOptional;
		entityOptional = repository.findAllByHabilitado(habilitado);
		try {
			if (entityOptional != null) {
				return entityOptional;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception();
		}
	};
}
