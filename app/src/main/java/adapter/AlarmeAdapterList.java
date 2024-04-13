package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.utfpr.gabrielfflobao.alarmhard.R;
import model.Alarme;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class AlarmeAdapterList extends ArrayAdapter<Alarme> {


    TextView nome;
    TextView hora;
    ArrayList<Alarme> list;

    private Context context;
    public AlarmeAdapterList(Context context,ArrayList<Alarme> list ) {
        super(context,0,list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.array_adapter_alarme, parent, false);
        }
        Alarme alarme = (Alarme) getItem(position);
        this.nome = view.findViewById(R.id.listaAlarmeNome);
        this.hora = view.findViewById(R.id.listaAlarmeHora);
        this.nome.setText(alarme.getNome());
        this.hora.setText(alarme.getHora().toString());
        return view;
    }

    public ArrayList<Alarme> getList() {
        return list;
    }

    public void setList(ArrayList<Alarme> list) {
        this.list = list;
    }
}
