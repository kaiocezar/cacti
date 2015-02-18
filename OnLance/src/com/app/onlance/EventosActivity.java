package com.app.onlance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.adapter.BaseAdapterEventos;
import com.app.bo.JogadorBo;
import com.app.facade.EventoFacade;
import com.app.vo.Evento;

import Utils.UtilsConstants;
import android.app.Activity;
import android.content.Intent;
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
	private JogadorBo bo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventos);
		bo  = new JogadorBo(this);
		init();
		bind();

	}

	private void bind() {
		criarEvento.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(EventosActivity.this, CriarEventoActivity.class);
				
				startActivity(intent);
				
				
			}
		});
		
		listViewEvento.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				EventoFacade evento = adapter.getItem(position);
				
				Intent itent = new Intent(EventosActivity.this,EventoPerfilActivity.class);
				itent.putExtra(UtilsConstants.ID_EVENTO, evento.getId());
				
				startActivity(itent);
				
				
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		init();
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

		List<Evento> evento = bo.getEventoByJogador();
		
		if(evento != null && evento.size() > 0){
			for(Evento e: evento){
				eventoParseFacade(retorno, e);
			}
			
		}
		
		return retorno;
	}
	
	public void eventoParseFacade(List<EventoFacade> retorno, Evento evento){
		
		EventoFacade facade = new EventoFacade();
		facade.setData(evento.getData());
		facade.setId(evento.getId());
		facade.setLocal(evento.getLocalizacao());
		facade.setNome(evento.getNome());
		retorno.add(facade);
	}

}
