using SoftwareDesignPrinciple;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorators
{
    internal class Dec_CakeLayer : CakeAbstract, ICakeItem
    {
        //My custom constructor
        public Dec_CakeLayer(ICakeItem subItem) : base(subItem)
        {
            
        }

        //Get Cost of all Items
        public double getCost()
        {
            const double COST = 3.50;
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
            string DESCRIPTION = " - Add a cake layer\n";
            if (_subItem != null)
                return DESCRIPTION + _subItem.getDesc();
            else
                return DESCRIPTION;
        }
    }
}
