package war.server.core.entity;

import net.sf.gilead.pojo.java5.LightEntity;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public abstract class BaseEntity extends LightEntity implements Serializable {

	private static final long serialVersionUID = -3858014970182092169L;

	@Id
	@Column(name="id", updatable=false, nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Version
	@Column(name="version")
	private int version = 0;


	public BaseEntity() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(getClass().getName());
		str.append("[id=");
		str.append(id);
		str.append("]");

		return str.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof BaseEntity))
            return false;

        final BaseEntity other = (BaseEntity) obj;
        if (this.id != null && other.id != null)
        	return this.id.equals(other.id);

        return false;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		if (id == null)
			return 0;
		else
			return id.hashCode();
	}


	/**
	 * Zwraca klucz/identyfikator obiektu.
	 *
	 * @return Identyfikator obiektu lub null jeżeli nie posiada on tożsamości.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Ustawia identyfikator obiektu.
	 *
	 * @param id nowa wartość identyfikatora.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Zwraca nr wersji obiektu.
	 * Protected - na potrzeby Gilead.
	 *
	 * @return wersja obiektu.
	 */
	protected int getVersion() {
		return this.version;
	}

	/**
	 * Ustawia nr wersji obiektu.
	 * Protected - na potrzeby Gilead.
	 *
	 * @param version nowa wersja obiektu.
	 */
	protected void setVersion(int version) {
		this.version = version;
	}
}
