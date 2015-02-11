package com.app.onlance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.app.bo.EventoBo;
import com.app.bo.JogadorBo;
import com.app.vo.Evento;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class CriarEventoActivity extends Activity {

	private Calendar calendar;
	private Button criarEvento;
	private TextView localEvento;
	private TextView recadoEvento;
	private TextView nomeEvento;
	private TextView dataEvento;
	private TextView horaEvento;
	private int year, month, day;
	private int hora, minuto;
	private EventoBo bo;
	private JogadorBo boJoogador;
	private DatePickerDialog.OnDateSetListener myDateListener;
	private TimePickerDialog.OnTimeSetListener myTimePickerLinistener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_evento);

		init();
		bind();

	}

	private void init() {
		bo = new EventoBo(this);
		boJoogador = new JogadorBo(this);
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hora = calendar.get(Calendar.HOUR_OF_DAY);
		minuto = calendar.get(Calendar.MINUTE);
		criarEvento = (Button) findViewById(R.id.criar_evento_evento);
		localEvento = (TextView) findViewById(R.id.local_evento);
		recadoEvento = (TextView) findViewById(R.id.mais_informacoes);
		nomeEvento = (TextView) findViewById(R.id.nome_evento);
		dataEvento = (TextView) findViewById(R.id.data_evento);
		horaEvento = (TextView) findViewById(R.id.horario_envento);

		showDate(year, month + 1, day);
		showHora(hora, minuto);

	}

	private void bind() {

		criarEvento.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Evento evento = new Evento();
				
				evento.setLocalizacao(localEvento.getText().toString());
				evento.setNome(nomeEvento.getText().toString());
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				String dateInString = dataEvento.getText().toString() + " " + horaEvento.getText().toString();
				try {
					Date date = sdf.parse(dateInString);
					evento.setData(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				bo.criarEvento(evento, boJoogador.findByIdPreferences());
				finish();
			}
		});

		dataEvento.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(999);
			}
		});

		horaEvento.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(1000);
			}
		});

		myDateListener = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker arg0, int year, int month, int day) {
				showDate(year, month + 1, day);
			}
		};

		myTimePickerLinistener = new OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

				showHora(hourOfDay, minute);

			}
		};
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if (id == 999) {
			return new DatePickerDialog(this, myDateListener, year, month, day);
		} else if (id == 1000) {
			return new TimePickerDialog(this, myTimePickerLinistener, hora,
					minuto, true);
		}
		return null;
	}

	private void showDate(int year, int month, int day) {
		dataEvento.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
	}

	private void showHora(int hora, int minuto) {
		horaEvento.setText(new StringBuilder().append(hora).append(":")
				.append(minuto));
	}

}
