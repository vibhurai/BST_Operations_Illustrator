/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_take2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 *
 * @author jkin
 */
class Node
{
    int data;
    Node left;
    Node right;
    
    public Node(int d)
    {
        this.data=d;
    }
    public int getdata()
    { 
        return data;
    }
    public Node getLeft() 
    { 
        return left; 
    }
    public Node setLeft(int data) 
    {
	left = new Node(data);
	return left;
    }
    public Node getRight() 
    { 
        return right; 
    }
    public Node setRight(int content) 
    {
	right = new Node(content);
	return right;
    }      
}

public class BinarySearchTree 
{
    Node root=null;
    
    public Node getRoot()
    {
        return root;
    }
    public Node addNode(int content) 
    {
        if (root == null) 
        {
            root = new Node(content);
            return root;        
        }
      return addTo(root, content);
    }
    private Node addTo(Node n, int c) 
    {
        if (c < n.getdata())
        {
            Node l = n.getLeft();
            if (l == null)
            return n.setLeft(c);
            else
            return addTo(l, c);        
        } 
        
        else 
        {
            Node r = n.getRight();
            if (r == null)
            return n.setRight(c);
            else
            return addTo(r, c);
        }
    }
    public Node searchNode(int d, Node n){
      Node aux = null; //Not found
      if (n != null){
          if (d == n.getdata()){
              return n; 
          }
            else {
                if (d < n.getdata()) {
                    Node der = n.getLeft();
                    aux = searchNode(d, der);
                }
                else {
                    Node izq = n.getRight();
                    aux = searchNode(d, izq);
                }
            }
        }        
       return aux;
    }
   
    int countNodes()
    {
        return countNodes(root);
    }
    int countNodes(Node n)
    {   int count;
        if(n==null)
        {
            return 0;
        }
        else
        {
            count=1;
            count=count+countNodes(n.getLeft());
            count=count+countNodes(n.getRight());
        }
        return count;
    }
    Node  deleteKey(int key)
    {
        Node n=deleteKey(root,key);
        return n;
    }

    Node deleteKey(Node root, int key) 
    { 
        if (root == null)  return root; 

        if (key < root.data) 
            root.left = deleteKey(root.left, key); 
        
        else if (key > root.data) 
            root.right = deleteKey(root.right, key); 
  
        else
        { 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 

            root.data = minValue(root.right); 
  
            // Delete the inorder successor 
            root.right = deleteKey(root.right, root.data); 
        } 
  
        return root; 
    } 
     int minValue(Node root) 
    { 
        int minv = root.data; 
        while (root.left != null) 
        { 
            minv = root.left.data; 
            root = root.left; 
        } 
        return minv; 
    } 
}

class TheWindow extends JPanel implements ActionListener
{      
       static JButton insert = new JButton("Insert"), delete = new JButton("Delete"), print = new JButton("Print"),find = new JButton("Search");
       static JTextField inserttf = new JTextField(""), deletetf = new JTextField(""), vertices = new JTextField("0"), height = new JTextField("0"), printtf = new JTextField(""), findtf = new JTextField("");
       static JTextField inordertf = new JTextField("Inorder: ");
       static JLabel ver = new JLabel("No. of Vertices : "), ht = new JLabel("Height of tree : ");
       static  String s1 = "";
   
       private BinarySearchTree tree = null;
       private HashMap nodeLocations = null;
       private HashMap subtreeSizes = null;
       private boolean calculation = true;
       private int dist1 = 50, dist2 = 60;
       private Dimension empty = new Dimension(0, 0);
       private FontMetrics fm = null;
       //static JFrame frame=new JFrame();
       public TheWindow(BinarySearchTree tree)
       {   
        
        //frame.setLayout(null);          
        this.tree = tree;
        nodeLocations = new HashMap();
        subtreeSizes = new HashMap();
        insert.addActionListener(this);
        delete.addActionListener(this);
        find.addActionListener(this);
        print.addActionListener(this);
        
       }
       
       public static void main(String[] args) {
        // TODO code application logic here
        BinarySearchTree tree = new BinarySearchTree();
        JFrame f = new JFrame("Binary Tree");        
        JPanel p1 = new JPanel();
        JPanel p3 = new JPanel();
        p1.setLayout(new GridLayout(1, 6));
        p1.setBounds(0, 0,1000, 50);
        f.setBackground(Color.black);
        JPanel p2 = new JPanel();
        f.getContentPane().add(new TheWindow(tree));
        inordertf.setForeground(Color.blue);
        insert.setBackground(Color.orange);
        delete.setBackground(Color.orange);
        find.setBackground(Color.orange);
        print.setBackground(Color.yellow);
        ver.setBackground(Color.orange);
        print.setBorder(new LineBorder(Color.black,4));
        
        inordertf.setBorder(new LineBorder(Color.orange));
        p1.setBorder(new LineBorder(Color.orange,5));
        p2.setBorder(new LineBorder(Color.orange,5));

        p1.add(inserttf);
        p1.add(insert);
        p1.add(deletetf);
        p1.add(delete);
        p1.add(findtf);
        p1.add(find);
        p1.add(print);
        p1.add(vertices);
       // p1.setLayout(new GridLayout(8,1));
        //inordertf.setBounds(100,600,800,30);
        
        //=================================
        
        p2.setLayout(new GridLayout(1, 5));
        p2.setBounds(100, 500, 800, 100);
        p2.add(ver);
        p2.add(vertices);
        p2.add(ht);
        p2.add(height);
        p2.add(inordertf);
        
        //=================================    
        p3.add(p2);
        p3.add(inordertf);
        p3.setLayout(new GridLayout(2,1));
        f.add(p1,BorderLayout.NORTH);
//        p3.setLayout(new GridLayout(2,1));
        f.add(p3,BorderLayout.SOUTH);
        
        f.setResizable(false);//function to prevent resizing
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 800);
        f.setVisible(true);  
       // f.setBounds(50, 50, 700, 700);
        //f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }  
       
       
        
        public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==insert)
        {   
            if(tree.searchNode(Integer.parseInt(inserttf.getText()), tree.getRoot())==null)
        {
            tree.addNode(Integer.parseInt(inserttf.getText()));
            calculation=true;  
            vertices.setText(Integer.toString(tree.countNodes()));
            height.setText(String.valueOf(ht(tree.root)));
            inordertf.setText(inserttf.getText()+" is inserted");
            repaint();
        }
            else
            {   
                inordertf.setText(inserttf.getText()+" is already present");
                s1 = " ";
            }
                 
        }
        if(ae.getSource()==delete)
        {   
            tree.deleteKey( tree.getRoot(),Integer.parseInt(deletetf.getText()));
            calculation=true;
            vertices.setText(Integer.toString(tree.countNodes()));      
            height.setText(String.valueOf(ht(tree.root)));
            inordertf.setText(deletetf.getText()+" is deleted");
            repaint();
        }
        if(ae.getSource()==find)
        {
            Node aux = tree.searchNode(Integer.parseInt(findtf.getText()), tree.getRoot());
            
            if (aux == null)
            {   
                inordertf.setText(findtf.getText()+" Not Found");
            }
            else
            {
                inordertf.setText(aux.data+" Found");
            }

            System.out.println(aux);
            calculation= true;
            height.setText(String.valueOf(ht(tree.root)));
            repaint();
        }
        if(ae.getSource()==print)
        {   
            printt(tree.getRoot());
            height.setText(String.valueOf(ht(tree.root)));
        s1 = " ";
        }
        
         
        
    }
    void printt(Node n)
    {
        if(n.left != null)
            printt(n.left);
        s1  = s1 +" " + String.valueOf(n.data); 
        inordertf.setText("The Inorder Traversal is " +s1);
        if(n.right != null)
            printt(n.right);
    }
      
    public void calculateLocations()
    {
        nodeLocations.clear();
        subtreeSizes.clear();
        Node root=tree.getRoot();
        if(root!=null)
        {
            calculateSubtreeSize(root);
            calculateLocation(root,Integer.MAX_VALUE,Integer.MAX_VALUE,0);
            
        }
    }
    public Dimension calculateSubtreeSize(Node n)
    {
        if(n==null)
                {
                    return new Dimension(0,0);
                    
                }
        Dimension ld= calculateSubtreeSize(n.getLeft());
        Dimension rd= calculateSubtreeSize(n.getRight());
        int h=fm.getHeight()+dist1+Math.max(ld.height, rd.height);
        int w=ld.width+dist2+rd.width;
        Dimension d=new Dimension (w,h);
        subtreeSizes.put(n, d);
        return d;
    }
    
      private void calculateLocation(Node n, int left, int right, int top) {
      if (n == null) 
      {
          return;
      }
      Dimension ld = (Dimension) subtreeSizes.get(n.getLeft());
      if (ld == null) 
      {
          ld = empty;
      }
      Dimension rd = (Dimension) subtreeSizes.get(n.getRight());
      if (rd == null) 
      {
          rd = empty;
      }
      int center = 0;
      if (right != Integer.MAX_VALUE)
      {
          center = right - rd.width - dist2/2;
      }
      else if (left != Integer.MAX_VALUE)
      {
          center = left + ld.width + dist2/2;
      }
      int width = fm.stringWidth(Integer.toString(n.getdata()));
      
      Rectangle r = new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight());
      nodeLocations.put(n, r);
      calculateLocation(n.getLeft(), Integer.MAX_VALUE, center - dist2/2, top + fm.getHeight() + dist1);
      calculateLocation(n.getRight(), center + dist2/2, Integer.MAX_VALUE, top + fm.getHeight() +dist1);
    }
    
      private void drawTree(Graphics2D g, Node n, int px, int py, int offset) {
      if (n == null) 
      {   
          return;
      }
      Rectangle r = (Rectangle) nodeLocations.get(n);
      g.setColor(Color.ORANGE);
      g.setStroke(new BasicStroke(4));
      g.draw(r);  
        g.setColor(Color.black);
       g.setStroke(new BasicStroke(1));
      g.drawString(Integer.toString(n.getdata()), r.x + 3, r.y + offset);
       if (px != Integer.MAX_VALUE)
       {g.drawLine(px, py, r.x + r.width/2, r.y);}
     drawTree(g, n.getLeft(), r.x + r.width/2, r.y + r.height, offset);
     drawTree(g, n.getRight(), r.x + r.width/2, r.y + r.height, offset);
   }
@Override
     public void paint(Graphics g) {
     super.paint(g);
     fm = g.getFontMetrics();
     // if node locations not calculated
     if (calculation) {
       calculateLocations();
       calculation = false;
     }
     Graphics2D g2d = (Graphics2D) g;
     g2d.setStroke(new BasicStroke(4));
     g2d.translate(getWidth() / 2, dist2);
     drawTree(g2d, tree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
     fm = null;
   }
     
      
      int ht(Node root)  //calculates the height
      { 
          if(root == null)
              return 0;
          else
          {
            int leftht, righttht ;
            leftht= ht(root.left);
            righttht = ht(root.right);
            if( leftht > righttht)
                 return leftht+1;
             else
                return righttht +1;
          }
              
      }
    
}