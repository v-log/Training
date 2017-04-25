def sorted?(a)
  for i in 0...a.length - 1
    if a[i] > a[i + 1]
      return false
    end
  end

  true
end

def selection_sort(a)
  @N = a.length
  for i in 0...@N
    min = i
    for j in i + 1...@N
      if a[j] < a[min]
        min = j
      end
    end

    a[i], a[min] = a[min], a[i]
  end
end

def selection_sort2(a)
  @N = a.length
  for i in 0...@N
    min = i
    for j in i + 1...@N
      var1 = block_given? ? yield(a[j]) : a[j]
      var2 = block_given? ? yield(a[min]) : a[min]
      if var1 < var2
        min = j
      end
    end

    a[i], a[min] = a[min], a[i]
  end
end

a = [5,3,2,1,4]
puts sorted?(a)
selection_sort(a)
raise unless sorted?(a)
puts a.inspect
puts sorted?(a)

a = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
selection_sort2(a) { |item| -item[1] }
puts a.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]
