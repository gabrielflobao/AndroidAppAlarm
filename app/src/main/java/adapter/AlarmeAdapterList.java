package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.utfpr.gabrielfflobao.alarmhard.R;
import model.Alarme;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class AlarmeAdapterList extends BaseAdapter {


    TextView nome;
    TextView hora;
    ArrayList<Alarme> list;

    private Activity act;

    public AlarmeAdapterList(ArrayList<Alarme> list, Activity act) {
        this.list = list;
        this.act = act;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view = act.getLayoutInflater().inflate(R.layout.lista_alarme_component,parent,false);
        Alarme alarme = list.get(position);
        this.nome = view.findViewById(R.id.listaAlarmeNome);
        this.hora = view.findViewById(R.id.listaAlarmeHora);
        this.nome.setText(alarme.getNome());
        this.hora.setText(alarme.getHora().toString());
        return view;
    }
}
