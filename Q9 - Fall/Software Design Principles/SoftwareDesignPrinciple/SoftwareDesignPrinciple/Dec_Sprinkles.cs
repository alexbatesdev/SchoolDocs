using SoftwareDesignPrinciple;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorators
{
    enum Shape
    {
        Circle,
        Square,
        Confetti,
        Stars,
        Shrapnel
    }

    class Dec_Sprinkles : CakeAbstract, ICakeItem
    {
        private Flavor _flavor;
        private Shape _shape;
        //My custom constructor
        public Dec_Sprinkles(ICakeItem subItem) : this(subItem, Flavor.Vanilla)
        {

        }
        public Dec_Sprinkles(ICakeItem subItem, Flavor flavor) : this(subItem, flavor, Shape.Confetti)
        {
            _flavor = flavor;
        }
        public Dec_Sprinkles(ICakeItem subItem, Flavor flavor, Shape shape) : base(subItem)
        {
            _flavor = flavor;
            _shape = shape;
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
            string DESCRIPTION = " - Add a sprinkles layer of flavor " + _flavor.ToString() + " and of shape " + _shape.ToString() + "\n";
            if (_subItem != null)
                return _subItem.getDesc() + DESCRIPTION;
            else
                return DESCRIPTION;
        }
    }
}
