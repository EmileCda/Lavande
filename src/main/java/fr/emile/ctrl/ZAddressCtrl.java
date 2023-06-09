package fr.emile.ctrl;

import fr.emile.entity.Address;
import fr.emile.model.CrudDao;
import fr.emile.utils.DataTest;
import fr.emile.utils.Utils;

public class ZAddressCtrl extends CrudCtrl implements IClassCtrl {

	private static final long serialVersionUID = 1L;
	//-------------------------------------------------------------------------------------------------
		public Address create(Address address) {

			CrudDao addressDao = new CrudDao(new Address());
			Address addressReturn;

			try {

				address = (Address) preWrite(address);
				addressReturn = (Address) addressDao.create(address);
			} catch (Exception e) {
				Utils.trace("catch create %s\n", e.toString());

			}
			return addressReturn;
		}

		//-------------------------------------------------------------------------------------------------
				public Address read(int id) {

					CrudDao addressDao = new CrudDao(new Address());
					Address addressReturn;

					try {

						addressReturn = (Address) addressDao.read(id);
						addressReturn  = (Address) postRead(addressReturn );
					} catch (Exception e) {
						Utils.trace("catch create %s\n", e.toString());

					}
					return addressReturn;
				}

				//-------------------------------------------------------------------------------------------------
				public Address list() {

					CrudDao addressDao = new CrudDao(new Address());
					Address addressReturn;

					try {

						addressReturn = (Address) addressDao.read(id);
					} catch (Exception e) {
						Utils.trace("catch create %s\n", e.toString());

					}
					return addressReturn;
				}

	@Override
	public Object preWrite(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object postWrite(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object preRead(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object postRead(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
