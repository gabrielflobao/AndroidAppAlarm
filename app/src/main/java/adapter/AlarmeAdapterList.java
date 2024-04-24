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
import java.util.List;

import br.edu.utfpr.gabrielfflobao.alarmhard.R;
import model.Alarme;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class AlarmeAdapterList extends BaseAdapter {

    private LayoutInflater inflater;
    TextView nome;
    TextView hora;
    List<Alarme> list;

    private Context context;
    public AlarmeAdapterList(Context context, List<Alarme> list ) {
        super();
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;

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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.array_adapter_alarme, parent, false);
        }
        Alarme alarme = (Alarme) getItem(position);
        this.nome = view.findViewById(R.id.listaAlarmeNome);
        this.hora = view.findViewById(R.id.listaAlarmeHora);
        this.nome.setText(alarme.getNome());
        this.hora.setText(alarme.getHora().toString());
        return view;
    }

    public List<Alarme> getList() {
        return list;
    }

    public void setList(List<Alarme> list) {
        this.list = list;
        notifyDataSetChanged();;
    }
}
