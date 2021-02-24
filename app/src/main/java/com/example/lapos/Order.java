package com.example.lapos;

public class Order {

    private Product product;
    private double solde = 0;
    private float qty=1;

    public Order(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double price() { return product.getPrice() - ( ( product.getPrice() * solde ) / 100 ); }

    public double getSolde() { return solde; }
    public String getStringSolde() {
        if(solde == (long) solde)
            return String.format("%d",(long)solde);
        else
            return String.format("%s",solde);
    }
    public double calculSolde() { return ( product.getPrice() * solde ) / 100 ; }
    public void setSolde(double solde) { this.solde = solde > 0 ? solde : 0 ; }

    public float getQty() { return qty; }
    public String getStringQty() {
        if(qty == (long) qty)
            return String.format("%d",(long)qty);
        else
            return String.format("%s",qty);
    }
    public void setQty(float q) { this.qty = q >= 1 ? q : 1 ; }
    public void upQty() { this.qty ++; }
    public void downQty() { if( this.qty > 1 ) this.qty --; }

    @Override
    public String toString() {
        if( this.solde == 0 )
            return this.product.toString();

        return this.product.toString() + "\n \n \t\t\t - Remise ( " + this.getStringSolde() + "% ) : -" + this.calculSolde() ;
    }
}
