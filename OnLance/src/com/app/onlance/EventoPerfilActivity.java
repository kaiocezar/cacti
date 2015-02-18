package com.app.onlance;

import java.util.ArrayList;
import java.util.List;

import Utils.UtilsConstants;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.adapter.BaseAdapterJogadoresEvento;
import com.app.bo.EventoBo;
import com.app.bo.JogadorBo;
import com.app.facade.JogadorFacade;
import com.app.facade.ParticipaFacadeAndroid;
import com.app.vo.Jogador;
import com.app.vo.Participa;

public class EventoPerfilActivity extends Activity {

	private EventoBo eventoBo;
	private JogadorBo jogadorBo;
	private int idEvento;
	private ListView listJogador;
	private BaseAdapterJogadoresEvento jogadorEvento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evento_info);
		eventoBo = new EventoBo(this);
		jogadorBo = new JogadorBo(this);
		idEvento = getIntent().getIntExtra(UtilsConstants.ID_EVENTO, 0);
		init();
		bind();

	}

	private void bind() {

	}

	private void init() {

		listJogador = (ListView) findViewById(R.id.listJogadorEvento);

		jogadorEvento = new BaseAdapterJogadoresEvento(this,
				getParticipaFacade());

		listJogador.setAdapter(jogadorEvento);

	}

	private List<ParticipaFacadeAndroid> getParticipaFacade() {
		List<ParticipaFacadeAndroid> retorno = new ArrayList<ParticipaFacadeAndroid>();

		List<Participa> list = eventoBo.getParticaEvento(idEvento);

		if (list != null && list.size() > 0) {
			for (Participa participa : list) {

				ParticipaFacadeAndroid p = new ParticipaFacadeAndroid();
				p.setConfirma(participa.isConfirma());
				p.setJogador(getJogadorFacade(participa.getJogador()));
			}
		}

		return retorno;
	}

	private JogadorFacade getJogadorFacade(Jogador jogador) {
		JogadorFacade retorno = new JogadorFacade();
		retorno.setEmail(jogador.getEmail());
		retorno.setNome(jogador.getNome());
		retorno.setFone(jogador.getNumeroTelefone());
		retorno.setId(jogador.getId());

		return retorno;
	}

}
