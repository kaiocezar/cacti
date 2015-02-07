package com.app.onlance;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class CriarEventoActivity extends Activity {

	private Calendar calendar;
	private TextView dataEvento;
	private TextView horaEvento;
	private int year, month, day;
	private int hora, minuto;
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
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hora = calendar.get(Calendar.HOUR_OF_DAY);
		minuto = calendar.get(Calendar.MINUTE);
		dataEvento = (TextView) findViewById(R.id.data_evento);
		horaEvento = (TextView) findViewById(R.id.horario_envento);
		
		showDate(year, month + 1, day);
		showHora(hora, minuto);


	}

	private void bind() {

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
			return new TimePickerDialog(this, myTimePickerLinistener, hora, minuto,true);
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
