package com.app.onlance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.adapter.BaseAdapterEventos;
import com.app.facade.EventoFacade;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class EventosActivity extends Activity {

	private ListView listViewEvento;
	private Button  criarEvento;
	private BaseAdapterEventos adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventos);

		init();
		bind();

	}

	private void bind() {
		criarEvento.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				listViewEvento.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						
						
						
					}
				});
			}
		});
		
		
	}

	private void init() {
		listViewEvento = (ListView) findViewById(R.id.listViewEvento);
		criarEvento = (Button) findViewById(R.id.criarEvento);

		List<EventoFacade> list = getEventoList();
		adapter = new BaseAdapterEventos(this, list);
		
		listViewEvento.setAdapter(adapter);
	}

	private List<EventoFacade> getEventoList() {

		List<EventoFacade> retorno = new ArrayList<EventoFacade>();


		EventoFacade facade1 = new EventoFacade();
		EventoFacade facade2 = new EventoFacade();
		EventoFacade facade3 = new EventoFacade();
		EventoFacade facade4 = new EventoFacade();
		
		
		facade1.setData(new Date());
		facade2.setData(new Date());
		facade3.setData(new Date());
		facade4.setData(new Date());

		
		List<Integer>intList1 = new ArrayList<Integer>();
		List<Integer>intList2 = new ArrayList<Integer>();
		List<Integer>intList3 = new ArrayList<Integer>();
		List<Integer>intList4 = new ArrayList<Integer>();
		
		intList1.add(1);
		intList2.add(1);
		intList2.add(1);
		intList3.add(1);
		intList3.add(1);
		intList3.add(1);
		intList4.add(1);
		intList4.add(1);
		intList4.add(1);
		intList4.add(1);
		
		facade1.setJogador(intList1);
		facade2.setJogador(intList2);
		facade3.setJogador(intList3);
		facade4.setJogador(intList4);
	
		retorno.add(facade1);
		retorno.add(facade2);
		retorno.add(facade3);
		retorno.add(facade4);
		
		
		return retorno;
		
	}

}
