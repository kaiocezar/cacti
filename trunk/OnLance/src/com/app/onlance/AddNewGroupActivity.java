package com.app.onlance;

import com.app.bo.GrupoBo;
import com.app.bo.JogadorBo;
import com.app.vo.Grupo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class AddNewGroupActivity extends Activity {

	private TextView nome;
	private TextView descricao;
	private Grupo grupo;
	private GrupoBo bo;
	private JogadorBo boJoogador;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_new_group_actions, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_add_new_group:


			grupo.setDescricao(descricao.getText().toString());
			grupo.setNome(nome.getText().toString());
			
			bo.saveInMembroAndAdministra(grupo, boJoogador.findByIdPreferences());
			
			
			finish();
			// openAddGroupDialog();
			return true;
		}

		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_group);
		
		nome = (TextView) findViewById(R.id.add_grupo_nome);
		descricao = (TextView) findViewById(R.id.add_grupo_descricao);
		grupo = new Grupo();
		
		boJoogador = new JogadorBo(this);
		bo = new GrupoBo(this);
		
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(R.string.novo_grupo_UPPER);
	}
	
}