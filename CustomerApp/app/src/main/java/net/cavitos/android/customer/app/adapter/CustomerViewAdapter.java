package net.cavitos.android.customer.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.cavitos.android.customer.app.R;
import net.cavitos.android.customer.app.domain.Customer;

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

    static class CustomerViewHolder extends RecyclerView.ViewHolder {

        private TextView edCustomerName;
        private TextView edCustomerCountry;
        private TextView edCustomerCompany;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            this.edCustomerName = itemView.findViewById(R.id.lbCustomerNameView);
            this.edCustomerCountry = itemView.findViewById(R.id.lbCustomerCountryView);
            this.edCustomerCompany = itemView.findViewById(R.id.lbCustomerCompanyView);
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
