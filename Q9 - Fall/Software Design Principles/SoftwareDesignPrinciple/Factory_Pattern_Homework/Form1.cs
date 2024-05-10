using Factory_Pattern_Homework.RealFactories;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Tab;
using System.Xml.Linq;
using System.IO;
//using System.Windows;

namespace Factory_Pattern_Homework
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        public List<AbstractComponent> componentsList = new List<AbstractComponent>();
        public ComponentFactory componentFactory = new ComponentFactory();
        public ComponentDisplay displayGenerator = new ComponentDisplay();
        public LanguageFactoryFactory LFF = new LanguageFactoryFactory();

        private void buttonAdd_Item_Click(object sender, EventArgs e)
        {
            Add_Options popup = new Add_Options(componentsList.Count);
            popup.ShowDialog();

            if (popup.DialogResult == DialogResult.Cancel) return;

            string component = popup.component;
            string backgroundColor = popup.backgroundColor;
            string text = popup.text;
            int left = popup.left;
            int top = popup.top;
            int width = popup.width;
            int height = popup.height;

            AbstractComponent newComponent = componentFactory.Create(component, backgroundColor, text, left, top, width, height);
            componentsList.Add(newComponent);

            RenderPreview(componentsList);
            
        }

        private void RenderPreview(List<AbstractComponent> components)
        {
            outputPreview.Controls.Clear();
            foreach (AbstractComponent component in components)
            {
                outputPreview.Controls.Add(displayGenerator.Create(component));
            }
        }

        private void buttonExport_Click(object sender, EventArgs e)
        {
            Form2 uppop = new Form2();
            uppop.ShowDialog();

            // This is just me messing around. Proper programming would have me not invert the boolean for no reason
            bool doExportHTML = !uppop.doExportXML;

            LanguageFactoryAbstract exportFactory;

            List<String> output = new List<string>();

            if (doExportHTML)
            {
                exportFactory = LFF.getFactory(Language.html);
                output.Add("<!DOCTYPE html>");
                output.Add("<html lang = \"en\">");
                output.Add("<head>");
                output.Add("<meta charset = \"UTF-8\">");
                output.Add("<meta name = \"viewport\" content = \"width=device-width, initial-scale=1.0\">");
                output.Add("<title>Document</title>");
                output.Add("</head>");
                output.Add("<body>");
            } else
            {
                exportFactory = LFF.getFactory(Language.xml);
                output.Add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                output.Add("<components>");

            }

            foreach (AbstractComponent component in componentsList)
            {
                string[] lines = exportFactory.Create(component).Split('\n');
                foreach (string line in lines)
                {
                    output.Add(line);
                }
            }

            if (doExportHTML)
            {
                exportFactory = LFF.getFactory(Language.html);
                output.Add("</body>");
                output.Add("</html>");
            }
            else
            {
                exportFactory = LFF.getFactory(Language.xml);
                output.Add("</components>");
            }

            using (StreamWriter writer = new StreamWriter("./export" + (doExportHTML ? ".html" : ".xml")))
            {

                Console.WriteLine("================================");
                foreach (var line in output)
                {
                    Console.WriteLine(line);
                    writer.WriteLine(line);
                }
                Console.WriteLine("================================");
            }

            string path = Path.GetFullPath("./export" + (doExportHTML ? ".html" : ".xml"));
            System.Diagnostics.Process.Start(path);


        }

        private void Undo_Click(object sender, EventArgs e)
        {
            componentsList.RemoveAt(componentsList.Count - 1);
            RenderPreview(componentsList);
        }
    }
}
