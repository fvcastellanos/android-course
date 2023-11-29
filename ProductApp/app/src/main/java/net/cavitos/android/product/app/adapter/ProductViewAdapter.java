package net.cavitos.android.product.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.cavitos.android.product.app.R;
import net.cavitos.android.product.app.domain.Product;

import java.util.List;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ProductViewHolder> {

    private final List<Product> products;

    public ProductViewAdapter(final List<Product> products) {

        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final var view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_product_view, null);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        final var product = products.get(position);

        holder.getLbProductName()
                .setText(product.getName());

        holder.getLbProductQuantity()
                .setText(Double.toString(product.getQuantity()));

        holder.getLbProductUnitPrice()
                .setText(Double.toString(product.getPrice()));

        holder.getLbProductTotal()
                .setText(Double.toString(product.getPrice() * product.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView lbProductName;
        private TextView lbProductQuantity;
        private TextView lbProductUnitPrice;
        private TextView lbProductTotal;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            lbProductName = itemView.findViewById(R.id.lbProductViewName);
            lbProductQuantity = itemView.findViewById(R.id.lbProductViewQuantity);
            lbProductTotal = itemView.findViewById(R.id.lbProductViewTotal);
            lbProductUnitPrice = itemView.findViewById(R.id.lbProductViewUnitPrice);
        }

        public TextView getLbProductName() {
            return lbProductName;
        }

        public TextView getLbProductQuantity() {
            return lbProductQuantity;
        }

        public TextView getLbProductUnitPrice() {
            return lbProductUnitPrice;
        }

        public TextView getLbProductTotal() {
            return lbProductTotal;
        }
    }
}
