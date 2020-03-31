package zi.common.ds.tree

import scala.collection.mutable

/**
  * @author balamurugan
  */
class IntervalTree {


  def doesOverlap(i1: Inter, i2: Inter): Boolean = i1.low <= i2.high && i2.low <= i1.high

  case class Node(var interval: Inter, var left: Node, var right: Node, var height: Int, var max: Int)

  def newNode(interval: Inter): Node = Node(interval, null, null, 1, interval.high)

  def height(node: Node): Int = if (node == null) 0 else node.height

  def balance(node: Node): Int = if (node == null) 0 else height(node.left) - height(node.right)

  def setMax(node: Node): Unit = {
    if (node == null) return
    var max: Int = -1
    if (node.left != null) max = node.left.max
    if (node.right != null) max = Math.max(max, node.right.max)
    node.max = Math.max(max, node.interval.high)
  }

  def leftRotate(x: Node): Node = {
    val y = x.right
    val T2 = y.left
    y.left = x
    x.right = T2
    x.height = Math.max(height(x.left), height(x.right)) + 1
    y.height = Math.max(height(y.left), height(y.right)) + 1
    setMax(x)
    setMax(y)
    y
  }

  def rightRotate(y: Node): Node = {
    val x = y.left
    val T2 = x.right
    x.right = y
    y.left = T2
    x.height = Math.max(height(x.left), height(x.right)) + 1
    y.height = Math.max(height(y.left), height(y.right)) + 1
    setMax(x)
    setMax(y)
    x
  }

  def minValue(node: Node): Node = {
    var current = node
    while (current.left != null) current = current.left
    current
  }

  var root: Node = _

  def insert(node: Node, interval: Inter): Node = {
    if (node == null) return newNode(interval)
    if (node.interval > interval) node.left = insert(node.left, interval)
    else node.right = insert(node.right, interval)
    node.height = Math.max(height(node.left), height(node.right)) + 1
    setMax(node)
    val balDiff = balance(node)
    if (balDiff > 1 && interval < node.left.interval) return rightRotate(node)
    if (balDiff < -1 && interval > node.right.interval) return leftRotate(node)
    if (balDiff > 1 && interval > node.left.interval) {
      node.left = leftRotate(node.left)
      return rightRotate(node)
    }
    if (balDiff < -1 && interval < node.right.interval) {
      node.right = rightRotate(node.right)
      return leftRotate(node)
    }
    node
  }

  def remove(root: Node, interval: Inter): Node = {
    var node = root
    if (node == null) return null
    if (interval < node.interval) node.left = remove(node.left, interval)
    if (interval > node.interval) node.right = remove(node.right, interval)
    if (node.left == null || node.right == null) {
      if (node.left == null && node.right == null) node = null
      else if (node.left == null) node = node.right else node = node.left
    } else {
      node.interval = minValue(node.right).interval
      node.right = remove(node.right, node.interval)
    }
    if (node == null) return null
    node.height = Math.max(height(node.left), height(node.right)) + 1
    setMax(node)
    val balDiff = balance(node)
    if (balDiff > 1 && balance(node.left) >= 0) return rightRotate(node)
    if (balDiff > 1 && balance(node.left) < 0) {
      node.left = leftRotate(node.left)
      return rightRotate(node)
    }
    if (balDiff < -1 && balance(node.right) <= 0) return leftRotate(node)
    if (balDiff < -1 && balance(node.right) > 0) {
      node.right = rightRotate(node.right)
      return leftRotate(node)
    }
    node
  }

  def remove(interval: Inter): Unit = {
    root = remove(root, interval)
  }

  def insert(interval: Inter): Unit = {
    root = insert(root, interval)
  }

  def overlapSearch(i: Inter, node: Node): Node = {
    if (node == null) return null
    if (doesOverlap(i, node.interval)) return node
    if (node.left != null && node.left.max >= i.low) overlapSearch(i, node.left)
    else overlapSearch(i, node.right)
  }

  def overlapSearch(i: Inter): Node = overlapSearch(i, root)

  def inOrderList(): mutable.ArrayBuffer[Inter] = inOrderList(root)

  def inOrderList(node: Node): mutable.ArrayBuffer[Inter] = {
    if (node == null) return mutable.ArrayBuffer[Inter]()
    val out = mutable.ArrayBuffer[Inter]()
    out ++= inOrderList(node.left)
    out += node.interval
    out ++= inOrderList(node.right)
    out
  }
}

case class Inter(low: Int, high: Int) extends Ordered[Inter] {
  override def compare(that: Inter): Int = low.compareTo(that.low)
}
