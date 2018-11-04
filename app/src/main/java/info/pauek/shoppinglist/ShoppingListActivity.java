package info.pauek.shoppinglist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    // TODO: 1. Afegir un CheckBox a cada ítem, per marcar o desmarcar els ítems (al model també!)
    // TODO: 2. Que es puguin afegir elements (+ treure els inicials)
    // TODO: 3. Afegir un menú amb una opció per esborrar de la llista tots els marcats
    // TODO: 4. Que es pugui esborrar un element amb LongClick (cal fer OnLongClickListener)

    // Model
    List<ShoppingItem> items;

    // Referències a elements de la pantalla
    private RecyclerView items_view;
    private ImageButton btn_add;
    private EditText edit_box;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        items = new ArrayList<>();
        //todo1: add false to the function call
        items.add(new ShoppingItem("Potatoes", false));
        items.add(new ShoppingItem("Toilet Paper", false));

        items_view = findViewById(R.id.items_view);
        btn_add = findViewById(R.id.btn_add);
        edit_box = findViewById(R.id.edit_box);

        adapter = new ShoppingListAdapter(this, items);

        items_view.setLayoutManager(new LinearLayoutManager(this));
        items_view.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        items_view.setAdapter(adapter);

        adapter.setOnClickListener(new ShoppingListAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                String msg = "Has clicat: " + items.get(position).getName();
                Toast.makeText(ShoppingListActivity.this, msg, Toast.LENGTH_SHORT).show();
                //todo1: changes boolean value when you click the box
                items.get(position).setChecked(!items.get(position).isChecked());
                //todo1: changes the layout to match the checkbox state
                adapter.notifyItemChanged(position);
            }
        });
        //todo4: when there is an onLongClick, it tells you there has been one and where
        adapter.setOnLongClickListener(new ShoppingListAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(int position) {
                //todo4: calls function to delete one item
                deleteItem(position);
            }
        });
    }

    //todo2: when you click the add item button it calls this function
    public void new_item(View view) {
        //todo2: saves the name you wrote to add it to the new item later
        String name = edit_box.getText().toString();
        //todo2: adds the item to the list, with checkbox not checked, adapter changes layout
        //and finally we set the edit text to blank again
        items.add(new ShoppingItem(name, false));
        adapter.notifyItemInserted(items.size());
        edit_box.setText("");
    }

    //todo3: adds menu to layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //todo3: deletes all the checked items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //todo3: case for when you click the Delete menu
            case R.id.Delete:
                for (int i = 0; i< items.size(); i++)
                {
                    //todo3: checks if item is checked before deleting it
                    if (items.get(i).isChecked())
                    {
                        items.remove(items.get(i));
                        //changes layout
                        adapter.notifyItemRemoved(i);
                        i--;
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //todo4: create function to delete the item in the position where you do the longClick
    public void deleteItem(final int position) {
        //todo4: creates pop up message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //todo4: sets pop up message title, text and options
        builder.setTitle("Confirm").setMessage("Are you sure you want to delete this item?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            //when you click the "yes" option it deletes the item
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                items.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }).setNegativeButton(android.R.string.cancel, null);
        //todo4: shows the pop up in screen
        builder.create().show();
    }
}
