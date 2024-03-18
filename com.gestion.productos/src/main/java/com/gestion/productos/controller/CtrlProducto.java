package com.gestion.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.productos.entity.Producto;
import com.gestion.productos.service.SvcProducto;

@Controller
public class CtrlProducto
{

	@Autowired
	private SvcProducto svcProducto;

	@RequestMapping("/")
	public String index(Model modelo, @Param("palabraClave") String palabraClave)
	{
		List<Producto> productos = svcProducto.listAll(palabraClave);
		modelo.addAttribute("productos",productos);
		modelo.addAttribute("palabraClave",palabraClave);

		return "index";
	}

	@RequestMapping("/nuevo")
	public String mostrarFormularioRegistroProducto(Model modelo)
	{
		Producto producto = new Producto();
		modelo.addAttribute("producto",producto);
		return "nuevo_producto";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardarProducto(@ModelAttribute("producto") Producto producto)
	{
		svcProducto.save(producto);
		return "redirect:/";
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView mostrarFormularioEditarProducto(@PathVariable(name = "id") Long id)
	{
		ModelAndView modelo = new ModelAndView("editar_producto");
		Producto producto = svcProducto.get(id);
		modelo.addObject("producto",producto);

		return modelo;
	}

	@RequestMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable(name = "id") Long id)
	{
		svcProducto.detele(id);
		return "redirect:/";
	}
}
