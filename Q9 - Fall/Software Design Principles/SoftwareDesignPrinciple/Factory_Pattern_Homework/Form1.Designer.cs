namespace Factory_Pattern_Homework
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.buttonAdd_Item = new System.Windows.Forms.Button();
            this.buttonExport = new System.Windows.Forms.Button();
            this.Undo = new System.Windows.Forms.Button();
            this.outputPreview = new System.Windows.Forms.GroupBox();
            this.SuspendLayout();
            // 
            // buttonAdd_Item
            // 
            this.buttonAdd_Item.Location = new System.Drawing.Point(14, 15);
            this.buttonAdd_Item.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.buttonAdd_Item.Name = "buttonAdd_Item";
            this.buttonAdd_Item.Size = new System.Drawing.Size(84, 29);
            this.buttonAdd_Item.TabIndex = 0;
            this.buttonAdd_Item.Text = "Add Item";
            this.buttonAdd_Item.UseVisualStyleBackColor = true;
            this.buttonAdd_Item.Click += new System.EventHandler(this.buttonAdd_Item_Click);
            // 
            // buttonExport
            // 
            this.buttonExport.Location = new System.Drawing.Point(802, 15);
            this.buttonExport.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.buttonExport.Name = "buttonExport";
            this.buttonExport.Size = new System.Drawing.Size(84, 29);
            this.buttonExport.TabIndex = 3;
            this.buttonExport.Text = "Export";
            this.buttonExport.UseVisualStyleBackColor = true;
            this.buttonExport.Click += new System.EventHandler(this.buttonExport_Click);
            // 
            // Undo
            // 
            this.Undo.Location = new System.Drawing.Point(711, 15);
            this.Undo.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Undo.Name = "Undo";
            this.Undo.Size = new System.Drawing.Size(84, 29);
            this.Undo.TabIndex = 4;
            this.Undo.Text = "Undo";
            this.Undo.UseVisualStyleBackColor = true;
            this.Undo.Click += new System.EventHandler(this.Undo_Click);
            // 
            // outputPreview
            // 
            this.outputPreview.Location = new System.Drawing.Point(14, 51);
            this.outputPreview.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.outputPreview.Name = "outputPreview";
            this.outputPreview.Padding = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.outputPreview.Size = new System.Drawing.Size(873, 496);
            this.outputPreview.TabIndex = 5;
            this.outputPreview.TabStop = false;
            this.outputPreview.Text = "Preview";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(900, 562);
            this.Controls.Add(this.outputPreview);
            this.Controls.Add(this.Undo);
            this.Controls.Add(this.buttonExport);
            this.Controls.Add(this.buttonAdd_Item);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button buttonAdd_Item;
        private System.Windows.Forms.Button buttonExport;
        private System.Windows.Forms.Button Undo;
        private System.Windows.Forms.GroupBox outputPreview;
    }
}

