package telephony.core.entity.jpa;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {

    @OneToMany(
            mappedBy = "contact",
            fetch    = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private Collection<ProductComplaint> productComplaints;

    @OneToMany(
            mappedBy = "contact",
            fetch    = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private Collection<SaleComplaint> salesComplaints;

    @OneToMany(
        mappedBy = "contact",
        fetch    = FetchType.LAZY,
        cascade = {
                CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
        }
    )
    private Collection<Delivery> deliveries;
    
    @Column(
        name     = "details",
        nullable = false,
        length   = 1000
    )
    private String details;
    
    @Id
    @Column(
        name           = "id",
        updatable      = false,
        nullable       = false
    )
    @GeneratedValue(
        strategy       = GenerationType.SEQUENCE,
        generator      = "contacts_seq"
    )
    @SequenceGenerator(
        name           = "contacts_seq",
        sequenceName   = "contacts_seq",
        allocationSize = 1
    )
    private Long id;
    
    @Column(
        name     = "label",
        nullable = false,
        length   = 100
    )
    private String label;
    
    @OneToMany(
        mappedBy = "contact",
        fetch    = FetchType.LAZY,
        cascade = {
                CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
        }
    )
    private Collection<Sale> sales;
   
    @CollectionTable(
    		name = "emails",
    		joinColumns = @JoinColumn(name = "contact_id")
    )
    @ElementCollection
    private Collection<Email> emails;

    @CollectionTable(
    		name = "phonenumbers",
    		joinColumns = @JoinColumn(name = "contact_id")
    )
    @ElementCollection
    private Collection<PhoneNumber> phonenumbers;

    @CollectionTable(
            name = "faxes",
            joinColumns = @JoinColumn(name = "contact_id")
    )
    @ElementCollection
    private Collection<Fax> faxes;

    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * asd.
     * @param delivery asd.
     */
    public void addDelivery(Delivery delivery) {
    	
    	if (deliveries.contains(delivery)) {
			return;
		}
    	
    	deliveries.add(delivery);
    	delivery.setContact(this);
		
	}
    
    /**
     * asd.
     * @return a.
     */
    public Collection<Email> getEmails() {
		return emails;
	}

    /**
     * asd.
     * @param emails a.
     */
	public void setEmails(Collection<Email> emails) {
		this.emails = emails;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Collection<PhoneNumber> getPhonenumbers() {
		return phonenumbers;
	}

	/**
	 * asd.
	 * @param phonenumbers a.
	 */
	public void setPhonenumbers(Collection<PhoneNumber> phonenumbers) {
		this.phonenumbers = phonenumbers;
	}

	/**
     * asd.
     * @param delivery asd.
     */
    public void removeDelivery(Delivery delivery) {
		
    	if (!deliveries.contains(delivery)) {
			return;
		}
    	
    	deliveries.remove(delivery);
    	delivery.setContact(null);		
	}
    
    /**
     * asd.
     * @param sale asd.
     */
    public void addSale(Sale sale) {
		
    	if (sales.contains(sale)) {
			return;
		}
    	
    	sales.add(sale);
    	sale.setContact(this);
	}
    
    /**
     * asd.
     * @param sale asd.
     */
    public void removeSale(Sale sale) {
    	
    	if (!sales.contains(sale)) {
			return;
		}
    	
    	sales.remove(sale);
    	sale.setContact(null);
	}

    /**
     * Do nothing.
     */
    public Contact() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets contact's label.
     * @return contact's label.
     */
    public final String getLabel() {
        return label;
    }

    /**
     * Sets contact's label.
     * @param label the label.
     */
    public final void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets contact's details.
     * @return contact's details.
     */
    public final String getDetails() {
        return details;
    }

    /**
     * Sets contact's details.
     * @param details the details.
     */
    public final void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets contact's deliveries.
     * @return Collection of deliveries.
     */
    public final Collection<Delivery> getDeliveries() {
        return deliveries;
    }

    /**
     * Gets contact's sales.
     * @return asd.
     */
    public final Collection<Sale> getSales() {
        return sales;
    }

    /**
     * Sets contact's deliveries.
     * @param deliveries of contact.
     */
    public final void setDeliveries(Collection<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    /**
     * Sets contact's sales.
     * @param sales of contact.
     */
    public final void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }

    /**
     * asd.
     * @return a.
     */
    public Collection<Fax> getFaxes() {
        return faxes;
    }

    /**
     * ds.
     * @param faxes a.
     */
    public void setFaxes(Collection<Fax> faxes) {
        this.faxes = faxes;
    }

    public void addEmail(Email email) {

        if (!this.emails.contains(email)) {
            this.emails.add(email);
        }
    }

    public void removeEmail(Email email) {

        if (this.emails.contains(email)) {
            this.emails.remove(email);
        }
    }

    public void addPhoneNumber(PhoneNumber phonenumber) {

        if (!this.phonenumbers.contains(phonenumber)) {
            this.phonenumbers.add(phonenumber);
        }
    }

    public void removePhoneNumber(PhoneNumber phonenumber) {

        if (this.phonenumbers.contains(phonenumber)) {
            this.phonenumbers.remove(phonenumber);
        }
    }

    public void addFax(Fax fax) {

        if (!this.faxes.contains(fax)) {
            this.faxes.add(fax);
        }
    }

    public void removeFax(Fax fax) {

        if (this.faxes.contains(fax)) {
            this.faxes.remove(fax);
        }
    }

    public void addProductComplaint(ProductComplaint pc) {

        if (!productComplaints.contains(pc)) {
            productComplaints.add(pc);
        }
    }

    public void removeProductComplaint(ProductComplaint pc) {

        if (productComplaints.contains(pc)) {
            productComplaints.remove(pc);
        }
    }

    public Collection<ProductComplaint> getProductComplaints() {
        return productComplaints;
    }

    public void setProductComplaints(Collection<ProductComplaint> productComplaints) {
        this.productComplaints = productComplaints;
    }

    public Collection<SaleComplaint> getSalesComplaints() {
        return salesComplaints;
    }

    public void setSalesComplaints(Collection<SaleComplaint> salesComplaints) {
        this.salesComplaints = salesComplaints;
    }

    public void addSaleComplaint(SaleComplaint sc) {

        if (!salesComplaints.contains(sc)) {
            salesComplaints.add(sc);
        }
    }

    public void removeSaleComplaint(SaleComplaint sc) {

        if (salesComplaints.contains(sc)) {
            salesComplaints.remove(sc);
        }
    }
}
