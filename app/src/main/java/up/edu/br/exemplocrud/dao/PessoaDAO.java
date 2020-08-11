package up.edu.br.exemplocrud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import up.edu.br.exemplocrud.model.Pessoa;
import up.edu.br.exemplocrud.util.Conexao;

public class PessoaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public PessoaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("cpf", pessoa.getCpf());
        values.put("telefone", pessoa.getTelefone());
        values.put("rg", pessoa.getRg());

        return banco.insert("pessoa", null, values);
    }

    public List<Pessoa> obterTodos(){

        List<Pessoa> pessoas = new ArrayList<>();
        Cursor cursor = banco.query("pessoa", new String[]{"id", "nome","cpf", "telefone","rg"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Pessoa a = new Pessoa();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            a.setRg(cursor.getString(4));
            pessoas.add(a);
        }
        return pessoas;
    }

    public void excluir(Pessoa a){
        banco.delete("pessoa", "id = ?", new String[]{a.getId().toString()} );
    }

    public void atualizar(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("cpf", pessoa.getCpf());
        values.put("telefone", pessoa.getTelefone());
        values.put("rg", pessoa.getRg());

        banco.update("pessoa", values, "id = ?", new String[]{pessoa.getId().toString()});
    }

}
