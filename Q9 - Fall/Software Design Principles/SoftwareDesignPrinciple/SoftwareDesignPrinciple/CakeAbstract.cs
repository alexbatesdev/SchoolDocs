using SoftwareDesignPrinciple;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorators
{
    public
    abstract class CakeAbstract
    {
        //pointer to subItem
        protected ICakeItem _subItem = null;

        public CakeAbstract(ICakeItem subItem)
        {
            //subItem gets stored
            _subItem = subItem;
        }
    }
}
