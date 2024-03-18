package com.gestion.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.productos.entity.Producto;
import com.gestion.productos.repository.RepoProducto;

@Service
public class SvcProducto
{
	@Autowired
	private RepoProducto repoProducto;
	
	public List<Producto> listAll(String palabraClave)
	{
		if (palabraClave != null)
		{
			return repoProducto.findAll(palabraClave);
		}
		return repoProducto.findAll();
	}

	public void save(Producto producto)
	{
		repoProducto.save(producto);
	}

	public Producto get(Long id)
	{
		return repoProducto.findById(id).get();
	}

	public void detele(Long id)
	{
		repoProducto.deleteById(id);
	}
	
}
