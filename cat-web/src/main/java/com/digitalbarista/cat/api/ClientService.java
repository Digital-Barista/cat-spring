package com.digitalbarista.cat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.digitalbarista.cat.dao.ClientDao;
import com.digitalbarista.cat.data.Client;

@Controller
@Transactional
public class ClientService {
	
	@Autowired
	private ClientDao clientDao;
	
	@RequestMapping(value="/clients",method={RequestMethod.GET})
	public List<Client> getAllClients()
	{
		return clientDao.getAllClients();
	}
	
	@RequestMapping(value="/clients/{id}",method={RequestMethod.GET})
	public Client getClient(@PathVariable long id)
	{
		return clientDao.getClient(id);
	}
	
	@RequestMapping(value="/clients",method={RequestMethod.POST})
	public Client saveClient(@RequestBody Client client)
	{
		return clientDao.save(client);
	}
}
