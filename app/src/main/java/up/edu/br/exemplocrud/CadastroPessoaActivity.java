package up.edu.br.exemplocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import up.edu.br.exemplocrud.dao.PessoaDAO;
import up.edu.br.exemplocrud.model.Pessoa;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private  EditText rg;
    private PessoaDAO dao;
    private Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCpf);
        telefone = findViewById(R.id.editTelefone);
        rg= findViewById(R.id.editText);
        dao = new PessoaDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("pessoa")){
            pessoa = (Pessoa) it.getSerializableExtra("pessoa");
            nome.setText(pessoa.getNome());
            cpf.setText(pessoa.getCpf());
            telefone.setText(pessoa.getTelefone());
            rg.setText(pessoa.getRg());
        }

    }

    public void salvar(View view){

        if (pessoa == null) {
            pessoa = new Pessoa();
            pessoa.setNome(nome.getText().toString());
            pessoa.setCpf(cpf.getText().toString());
            pessoa.setTelefone(telefone.getText().toString());
            pessoa.setRg(rg.getText().toString());

            long id = dao.inserir(pessoa);
            Toast.makeText(this, "Pessoa inserido com ID " + id, Toast.LENGTH_SHORT).show();
        } else {
            pessoa.setNome(nome.getText().toString());
            pessoa.setCpf(cpf.getText().toString());
            pessoa.setTelefone(telefone.getText().toString());
            pessoa.setRg(rg.getText().toString());

            dao.atualizar(pessoa);
            Toast.makeText(this, "Dados atualizados", Toast.LENGTH_SHORT).show();
        }
    }
}
