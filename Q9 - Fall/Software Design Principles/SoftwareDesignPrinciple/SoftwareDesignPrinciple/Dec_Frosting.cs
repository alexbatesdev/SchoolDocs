using SoftwareDesignPrinciple;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorators
{
    enum Flavor
    {
        Vanilla,
        Chocolate,
        BirthdayCake
    }

    internal class Dec_Frosting : CakeAbstract, ICakeItem
    {
        

        private Flavor _flavor;
        //My custom constructor
        public Dec_Frosting(ICakeItem subItem) : this(subItem, Flavor.Vanilla)
        {

        }
        public Dec_Frosting(ICakeItem subItem, Flavor flavor) : base(subItem)
        {
            _flavor = flavor;
        }

        //Get Cost of all Items
        public double getCost()
        {
            const double COST = 1.50;
            if (_subItem != null)
            {
                return _subItem.getCost() + COST;
            }
            else
            {
                return COST;
            }
        }

        //Get description
        public string getDesc()
        {
            string DESCRIPTION = " - Add a frosting layer of flavor " + _flavor.ToString() + "\n";
            if (_subItem != null)
                return DESCRIPTION + _subItem.getDesc();
            else
                return DESCRIPTION;
        }
    }
}
