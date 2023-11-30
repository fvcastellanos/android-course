package net.cavitos.android.customer.app.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.cavitos.android.customer.app.R;
import net.cavitos.android.customer.app.domain.Customer;
import net.cavitos.android.customer.app.layout.CustomerDetailLayout;

import java.util.List;

public class CustomerViewAdapter extends RecyclerView.Adapter<CustomerViewAdapter.CustomerViewHolder> {

    private final List<Customer> customers;

    public CustomerViewAdapter(final List<Customer> customers) {

        this.customers = customers;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final var view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_customer_view, null);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

        final var customer = customers.get(position);

        holder.getEdCustomerName()
                .setText(customer.getName());

        holder.getEdCustomerCountry()
                .setText(customer.getCountry());

        holder.getEdCustomerCompany()
                .setText(customer.getCompany());
    }

    @Override
    public int getItemCount() {

        return customers.size();
    }

    // ---------------------------------------------------------------------------------------

    class CustomerViewHolder extends RecyclerView.ViewHolder {

        private final TextView edCustomerName;
        private final TextView edCustomerCountry;
        private final TextView edCustomerCompany;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            this.edCustomerName = itemView.findViewById(R.id.lbCustomerNameView);
            this.edCustomerCountry = itemView.findViewById(R.id.lbCustomerCountryView);
            this.edCustomerCompany = itemView.findViewById(R.id.lbCustomerCompanyView);

            itemView.setOnClickListener(view -> {

                final var context = itemView.getContext();

                final var customerId = customers.get(getAdapterPosition())
                        .getId();

                final var intent = new Intent(context, CustomerDetailLayout.class);
                intent.putExtra("customerId", customerId);

                context.startActivity(intent);
            });
        }

        public TextView getEdCustomerName() {
            return edCustomerName;
        }

        public TextView getEdCustomerCountry() {
            return edCustomerCountry;
        }

        public TextView getEdCustomerCompany() {
            return edCustomerCompany;
        }
    }
}
