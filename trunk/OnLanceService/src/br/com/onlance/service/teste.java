package br.com.onlance.service;

import br.com.onlance.bean.Pessoa;

import com.google.gson.Gson;

public class teste {

	public static void main(String[] args) {
		
		String as = "{\"id\":\"1\",\"nome\":\"kaio\",\"sexo\":\"m\",\"email\":\"kaio@kaio.com\"}";
		
		Gson g = new Gson();
		
		Pessoa p = g.fromJson(as, Pessoa.class);
		
		System.out.println(p.getNome());
	}

}
