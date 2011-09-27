package com.digitalbarista.cat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.digitalbarista.cat.dao.ClientDao;
import com.digitalbarista.cat.data.Client;

@Service("/clients")
@Transactional
public class ClientService {
	
	@Autowired
	private ClientDao clientDao;
	
	@RequestMapping(method={RequestMethod.GET})
	public List<Client> getAllClients()
	{
		return clientDao.getAllClients();
	}
	
}
